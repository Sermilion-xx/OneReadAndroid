package net.oneread.oneread.ui.registration

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.disposables.CompositeDisposable
import net.oneread.oneread.R
import net.oneread.oneread.ui.base.BaseActivity
import org.jetbrains.anko.toast
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class RegActivity : BaseActivity(), RegContract.View {

    @Inject lateinit var presenter: RegPresenter

    @BindView(R.id.username) lateinit var vUsername: EditText
    @BindView(R.id.email) lateinit var vEmail: EditText
    @BindView(R.id.password) lateinit var vPassword: EditText
    @BindView(R.id.password_again) lateinit var vPasswordAgain: EditText
    @BindView(R.id.login) lateinit var vLogin: Button

    lateinit var textChangeCompositeDisposables: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent.inject(this)
        setContentView(R.layout.activity_registration)
        ButterKnife.bind(this)
        presenter.attachView(this)
        subscribeChanges()
    }

    fun subscribeChanges() {
        textChangeCompositeDisposables = CompositeDisposable(

                RxTextView.textChanges(vUsername).skip(1).doOnNext {
                    val username = vUsername.text.toString()
                    if (username.isEmpty() || username.length < 3 || username.length > 20)
                        vUsername.error = getString(R.string.error_username)
                }.sample(1, TimeUnit.SECONDS).subscribe(),

                RxTextView.textChanges(vEmail).skip(1).doOnNext {
                    if (!presenter.validateEmail(vEmail.text.toString()))
                        vEmail.error = getString(R.string.error_email)
                }.sample(1, TimeUnit.SECONDS).subscribe(),

                RxTextView.textChanges(vPassword).skip(1).doOnNext {
                    if (!presenter.validatePassword(vPassword.text.toString()))
                        vPassword.error = getString(R.string.error_password)
                }.sample(1, TimeUnit.SECONDS).subscribe(),

                RxTextView.textChanges(vPasswordAgain).skip(1).doOnNext {
                    if (vPasswordAgain.text.toString() != vPassword.text.toString())
                        vPasswordAgain.error = getString(R.string.error_password_mismatch)
                }.sample(1, TimeUnit.SECONDS).subscribe()
        )
    }

    @OnClick(R.id.reg_next, R.id.login)
    fun handleClicks(view: View) {
        when (view.id) {
            R.id.reg_next -> {
                if (vUsername.error != null && vEmail.error!= null
                        && vPassword.error!= null && vPasswordAgain.error!= null) {

                    presenter.register(vUsername.text.toString(),
                            vEmail.text.toString(),
                            vPassword.text.toString(),
                            vPasswordAgain.text.toString(),
                            Locale.getDefault().language)
                }

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

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        textChangeCompositeDisposables.dispose()
    }
}
