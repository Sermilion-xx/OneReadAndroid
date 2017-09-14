package net.oneread.oneread.ui.login

import android.accounts.Account
import android.accounts.AccountManager
import android.content.SharedPreferences
import android.os.Bundle
import com.google.gson.Gson
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import net.oneread.oneread.R
import net.oneread.oneread.data.DataManager
import net.oneread.oneread.data.model.api.LoginResponse
import net.oneread.oneread.injection.ConfigPersistent
import net.oneread.oneread.onereadauth.account.AccountAuthenticator
import net.oneread.oneread.onereadauth.account.AccountUtil
import net.oneread.oneread.onereadauth.account.SessionManager
import net.oneread.oneread.onereadauth.models.Scope
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.util.concurrent.CompletableFuture
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

    private var disposables: CompositeDisposable = CompositeDisposable()

    override fun login(email: String, password: String) {
        dataManager.login(email, password).subscribe(object : Observer<LoginResponse> {

            override fun onComplete() {
                view.hideProgress()
            }

            override fun onError(e: Throwable) {
                processError(e)
            }

            override fun onSubscribe(d: Disposable) {
                disposables.add(d)
                view.showProgress()
            }

            override fun onNext(value: LoginResponse) {
                val accountManager = AccountManager.get(view.getContext())
                val account = Account(email, AccountUtil.ACCOUNT_TYPE)
                accountManager.addAccountExplicitly(
                        account,
                        null,
                        null
                )
                accountManager.setAuthToken(
                        account,
                        Scope.ALL_SCOPE.toString(),
                        value.data.token
                )
                view.onLoginSuccess()
            }
        })
    }

    override fun loginAnonymous(grant_type: String, device_id: String) {
        dataManager.loginAnonimous(grant_type, device_id).subscribe(object : Observer<LoginResponse> {

            override fun onComplete() {
                view.hideProgress()
            }

            override fun onError(e: Throwable) {
                processError(e)
            }

            override fun onSubscribe(d: Disposable) {
                disposables.add(d)
                view.showProgress()
            }

            override fun onNext(value: LoginResponse) {
                sharedPreferences.edit().putString(TOKEN, value.data.token).apply()
                view.onLoginSuccess()
            }
        })
    }

    private fun processError(e: Throwable) {
        view.hideProgress()
        val message = when (e) {
            is SocketTimeoutException -> getString(R.string.error_login_server)
            is HttpException -> {
                val response = Gson().fromJson(e.response().errorBody().string(), LoginResponse::class.java)
                response.error
            }
            else -> getString(R.string.error_unknown)
        }
        view.onLoginFail(message)
    }

    override fun detachView() {
        disposables.dispose()
        super.detachView()
    }

    companion object {
        val TOKEN = "token"
    }

    fun getString(string: Int): String = view.getContext().getString(string)

}