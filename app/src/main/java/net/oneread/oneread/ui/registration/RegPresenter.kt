package net.oneread.oneread.ui.registration

import android.content.Context
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import net.oneread.oneread.R
import net.oneread.oneread.data.DataManager
import net.oneread.oneread.data.model.RegResponse
import net.oneread.oneread.injection.ApplicationContext
import net.oneread.oneread.injection.ConfigPersistent
import retrofit2.HttpException
import rx.Subscription
import javax.inject.Inject

/**
 * ---------------------------------------------------
 * Created by Sermilion on 22/07/2017.
 * Project: OneRead
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */
@ConfigPersistent
class RegPresenter
@Inject
constructor(private val dataManager: DataManager, @ApplicationContext private val context: Context) : RegContract.Presenter() {

    private var subscription: Subscription? = null

    override fun detachView() {
        super.detachView()
        subscription?.unsubscribe()
    }

    override fun register(username: String,
                          email: String,
                          password: String,
                          passwordAgain: String,
                          ilang: String) {
        val errors = validateInput(username, email, password, passwordAgain)
        if (errors.isEmpty()) {
            dataManager.register(username = username,
                    email = email,
                    password = password,
                    name = "",
                    ilang = ilang)
                    .subscribe(object : Observer<RegResponse> {

                        override fun onComplete() {
                            view.hideProgress()
                        }

                        override fun onNext(value: RegResponse) {
                            view.onRegistrationSuccess()
                        }

                        override fun onSubscribe(d: Disposable?) {
                            view.showProgress()
                        }

                        override fun onError(e: Throwable) {
                            if (e is HttpException) {
                                val gson = Gson()
                                val response = gson.fromJson(e.response().errorBody().string(), RegResponse::class.java)
                                view.showFail(response.error)
                            } else {
                                view.showFail(context.getString(R.string.error_unknown))
                            }
                            view.hideProgress()
                        }
                    })
        } else {
            view.showValidationErrors(errors)
        }
    }

    private fun validateInput(username: String,
                              email: String,
                              password: String,
                              passwordAgain: String): List<ValidationError> {

        val errors = mutableListOf<ValidationError>()

        if (!validateEmail(email)) {
            errors.add(ValidationError.EMAIL)
        }
        if (password != passwordAgain) {
            errors.add(ValidationError.PASSWORD_MISMATCH)
        } else if (passwordAgain.isEmpty()) {
            errors.add(ValidationError.PASSWORD)
        }
        if (!validatePassword(password)) {
            errors.add(ValidationError.PASSWORD)
        }
        if (username.isEmpty() && username.length < 3 && username.length > 20) {
            errors.add(ValidationError.USERNAME)
        }
        return errors
    }

    private fun validateEmail(email: String)
            = email.contains("@")
            && email.contains(".")
            && email.length > 5

    private fun validatePassword(password: String)
            = password.matches(Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+\$"))

}