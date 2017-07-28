package net.oneread.oneread.ui.login

import net.oneread.oneread.ui.base.BasePresenter
import net.oneread.oneread.ui.base.MvpView

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
        fun showSuccess()
        fun showFail(error: String?)
        fun showProgress()
        fun hideProgress()
    }

    abstract class Presenter: BasePresenter<View>() {
        abstract fun login(email: String, password: String)
    }

}