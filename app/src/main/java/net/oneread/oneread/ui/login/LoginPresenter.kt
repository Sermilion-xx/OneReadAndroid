package net.oneread.oneread.ui.login

import android.content.SharedPreferences
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import net.oneread.oneread.data.DataManager
import net.oneread.oneread.data.model.LoginResponse
import net.oneread.oneread.injection.ConfigPersistent
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
            private val sharedPreferences: SharedPreferences) : LoginContract.Presenter() {

    override fun login(email: String, password: String) {
        dataManager.login(email, password).subscribe(object : Observer<LoginResponse> {

            override fun onComplete() {
                view.hideProgress()
            }

            override fun onError(e: Throwable) {
                view.hideProgress()
                view.showFail(e.message)
            }

            override fun onSubscribe(d: Disposable?) {
                view.showProgress()
            }

            override fun onNext(value: LoginResponse) {
                sharedPreferences.edit().putString(TOKEN, value.token).apply()
                view.showSuccess()
            }

        })
    }

    companion object {
        val TOKEN = "token"
    }

}