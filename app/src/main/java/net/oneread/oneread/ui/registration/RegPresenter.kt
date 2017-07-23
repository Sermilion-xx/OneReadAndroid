package net.oneread.oneread.ui.registration

import android.content.Context
import android.widget.EditText
import com.google.gson.Gson
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import net.oneread.oneread.R
import net.oneread.oneread.data.DataManager
import net.oneread.oneread.data.model.RegResponse
import net.oneread.oneread.injection.ApplicationContext
import net.oneread.oneread.injection.ConfigPersistent
import retrofit2.HttpException
import rx.Subscription
import java.util.concurrent.TimeUnit
import java.util.function.Consumer
import javax.inject.Inject

/**
 * ---------------------------------------------------
 * Created by Sermilion on 22/07/2017.
 * Project: OneRead
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.oneread.net</a>
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
    }

    fun validateEmail(email: String)
            = email.contains("@")
            && email.contains(".")
            && email.length > 5

    fun validatePassword(password: String)
            = password.matches(Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+\$"))




}