package net.oneread.oneread.ui.registration

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import net.oneread.oneread.R
import net.oneread.oneread.ui.base.BaseActivity
import org.jetbrains.anko.toast
import java.util.*
import javax.inject.Inject

class RegActivity : BaseActivity(), RegContract.View {

    @Inject lateinit var presenter: RegPresenter

    @BindView(R.id.username) lateinit var vUsername: EditText
    @BindView(R.id.email) lateinit var vEmail: EditText
    @BindView(R.id.password) lateinit var vPassword: EditText
    @BindView(R.id.password_again) lateinit var vPasswordAgain: EditText
    @BindView(R.id.login) lateinit var vLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
        setContentView(R.layout.activity_registration)
        ButterKnife.bind(this)
        presenter.attachView(this)
    }

    @OnClick(R.id.reg_next, R.id.login)
    fun handleClicks(view: View) {
        when (view.id) {
            R.id.reg_next -> {
//                presenter.register(vUsername.text.toString(),
//                        vEmail.text.toString(),
//                        vPassword.text.toString(),
//                        vPasswordAgain.text.toString(),
//                        Locale.getDefault().language)
                presenter.register(username = "Sermilion",
                        email = "scat95@bk.ru",
                        password = "Emocore95",
                        passwordAgain = "Emocore95",
                        ilang = Locale.getDefault().language)
            }
            R.id.login -> {
                //TODO: go back to login activity
            }
        }
    }

    override fun onRegistrationSuccess() {
        toast(R.string.registration_success)
    }

    override fun showFail(error: String) {
        toast(error)
    }

    override fun showValidationErrors(errors: List<ValidationError>) {
        errors.forEach {
            when (it) {
                ValidationError.EMAIL -> {
                    vEmail.error = getString(R.string.error_email)
                }
                ValidationError.USERNAME -> {
                    vUsername.error = getString(R.string.error_username)
                }
                ValidationError.PASSWORD -> {
                    vPassword.error = getString(R.string.error_password)
                }
                ValidationError.PASSWORD_MISMATCH -> {
                    vPasswordAgain.error = getString(R.string.error_password_mismatch)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
