package net.oneread.oneread.ui.login

import android.content.SharedPreferences
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import net.oneread.oneread.R
import net.oneread.oneread.data.DataManager
import net.oneread.oneread.data.model.LoginResponse
import net.oneread.oneread.injection.ConfigPersistent
import retrofit2.HttpException
import java.net.SocketTimeoutException
import javax.inject.Inject

/**
 * ---------------------------------------------------
 * Created by Sermilion on 28/07/2017.
 * Project: OneRead
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */

@ConfigPersistent
class LoginPresenter
@Inject
constructor(private val dataManager: DataManager,
            private val sharedPreferences: SharedPreferences) : LoginContract.MvpPresenter() {

    private var disposable: Disposable? = null

    override fun login(email: String, password: String) {
        dataManager.login(email, password).subscribe(object : Observer<LoginResponse> {

            override fun onComplete() {
                view.hideProgress()
            }

            override fun onError(e: Throwable) {
                view.hideProgress()
                val message:String
                when(e) {
                    is SocketTimeoutException -> message = getString(R.string.error_login_server)
                    is HttpException -> {
                        val response = Gson().fromJson(e.response().errorBody().string(), LoginResponse::class.java)
                        message = response.error
                    }
                    else -> message = getString(R.string.error_unknown)
                }
                view.onLoginFail(message)
            }

            override fun onSubscribe(d: Disposable) {
                disposable = d
                view.showProgress()
            }

            override fun onNext(value: LoginResponse) {
                sharedPreferences.edit().putString(TOKEN, value.data.token).apply()
                view.onLoginSuccess()
            }
        })
    }

    override fun detachView() {
        disposable?.dispose()
        super.detachView()
    }

    companion object {
        val TOKEN = "token"
    }

    fun getString(string: Int): String {
        return view.getContext().getString(string)
    }

}