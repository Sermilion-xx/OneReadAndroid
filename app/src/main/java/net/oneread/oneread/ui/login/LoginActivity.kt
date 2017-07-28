package net.oneread.oneread.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.disposables.CompositeDisposable
import net.oneread.oneread.R
import net.oneread.oneread.ui.base.BaseActivity
import net.oneread.oneread.ui.registration.RegActivity
import net.oneread.oneread.util.extension.validatePassword
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginContract.View {

    @Inject lateinit var presenter: LoginPresenter
    @BindView(R.id.email) lateinit var vEmail: EditText
    @BindView(R.id.password) lateinit var vPassword: EditText

    lateinit var textChangeCompositeDisposables: CompositeDisposable

    override fun showSuccess() {

    }

    override fun showFail(error: String?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        activityComponent.inject(this)
        ButterKnife.bind(this)
        presenter.attachView(this)
        subscribeChanges()
    }



    @OnClick(R.id.register, R.id.login, R.id.forgot_password)
    fun handleClicks(view: View) {
        when (view.id) {
            R.id.login -> {
                val inputNoErrors = vEmail.error == null && vPassword.error == null
                val inputNotEmpty = vEmail.text.isNotEmpty() && vPassword.text.isNotEmpty()
                if (inputNoErrors && inputNotEmpty) {
                    presenter.login(vEmail.text.toString(), vPassword.text.toString())
                }
            }
            R.id.register -> {
                startActivity(RegActivity.createIntent(this))
                finish()
            }
            R.id.forgot_password -> {

            }
        }
    }

    fun subscribeChanges() {
        textChangeCompositeDisposables = CompositeDisposable(
                RxTextView.textChanges(vEmail).skip(1).doOnNext {
                    val username = vEmail.text.toString()
                    if (username.isEmpty() || username.length < 3 || username.length > 20)
                        vEmail.error = getString(R.string.error_username)
                }.sample(1, TimeUnit.SECONDS).subscribe(),

                RxTextView.textChanges(vPassword).skip(1).doOnNext {
                    if (!validatePassword(vPassword.text.toString()))
                        vPassword.error = getString(R.string.error_password)
                }.sample(1, TimeUnit.SECONDS).subscribe()
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        textChangeCompositeDisposables.dispose()
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
}
