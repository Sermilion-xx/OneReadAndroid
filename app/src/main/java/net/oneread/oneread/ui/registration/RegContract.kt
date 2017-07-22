package net.oneread.oneread.ui.registration

import net.oneread.oneread.data.model.Article
import net.oneread.oneread.ui.base.BasePresenter
import net.oneread.oneread.ui.base.MvpView

/**
 * ---------------------------------------------------
 * Created by Sermilion on 22/07/2017.
 * Project: OneRead
 * ---------------------------------------------------
 * <a href="http://www.ucomplex.org">www.ucomplex.org</a>
 * <a href="http://www.github.com/sermilion>github</a>
 * ---------------------------------------------------
 */
object RegContract {

    interface View: MvpView {
        fun onRegistrationSuccess()
        fun showFail(error: String)
        fun showValidationErrors(errors: List<ValidationError>)
        fun showProgress()
        fun hideProgress()
    }

    abstract class Presenter: BasePresenter<View>() {
        abstract fun register(username: String,
                              email: String,
                              password: String,
                              passwordAgain: String,
                              ilang: String)
    }


}

enum class ValidationError {
    EMAIL, PASSWORD, PASSWORD_MISMATCH, USERNAME
}

