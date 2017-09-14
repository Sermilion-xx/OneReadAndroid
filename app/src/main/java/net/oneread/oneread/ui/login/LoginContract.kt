package net.oneread.oneread.ui.login

import android.content.Context
import net.oneread.oneread.ui.base.BaseMvpPresenter
import net.oneread.oneread.ui.base.MvpView
import java.util.concurrent.CompletableFuture

/**
 * ---------------------------------------------------
 * Created by Sermilion on 28/07/2017.
 * Project: OneRead
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */
class LoginContract {

    interface View: MvpView {
        fun onLoginSuccess()
        fun onLoginFail(error: String)
        fun showProgress()
        fun hideProgress()
        fun getContext(): Context
    }

    abstract class MvpPresenter : BaseMvpPresenter<View>() {
        abstract fun login(email: String, password: String)
        abstract fun loginAnonymous(grant_type: String, device_id: String)
    }

}