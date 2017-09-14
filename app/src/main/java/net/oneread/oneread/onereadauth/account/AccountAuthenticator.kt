package net.oneread.oneread.onereadauth.account

import android.accounts.AbstractAccountAuthenticator
import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import net.oneread.oneread.OneReadApplication
import net.oneread.oneread.data.DataManager
import net.oneread.oneread.ui.login.LoginActivity
import javax.inject.Inject

/**
 * Created by sermilion on 9/10/17.
 */

class AccountAuthenticator constructor(private val mContext: Context) : AbstractAccountAuthenticator(mContext) {

    @Inject lateinit var dataManager: DataManager

    init {
        OneReadApplication.INSTANCE.applicationComponent.inject(this)
    }

    companion object {
        private val GRANT_TYPE_ANONYMOUS = "grant_type_anonimous"
        val USER_DATA_EXPIRATION = "net.oneread.expiration"
        val KEY_ACCOUNT_TYPE = "net.oneread.account_type"
        val KEY_TOKEN_TYPE = "net.oneread.token_type"
    }

    override fun editProperties(response: AccountAuthenticatorResponse, accountType: String): Bundle? {
        return null
    }

    override fun addAccount(response: AccountAuthenticatorResponse,
                            accountType: String,
                            authTokenType: String,
                            requiredFeatures: Array<String>,
                            options: Bundle): Bundle {
        val intent = Intent(mContext, LoginActivity::class.java)
        intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, accountType)
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)
        val isRegistration = options.getBoolean(SessionManager.ARG_IS_REGISTRATION, false)
        intent.putExtra(
                LoginActivity.EXTRA_REGISTRATION,
                isRegistration
        )
        val bundle = Bundle()
        bundle.putParcelable(AccountManager.KEY_INTENT, intent)
        return bundle
    }

    override fun confirmCredentials(response: AccountAuthenticatorResponse, account: android.accounts.Account, options: Bundle): Bundle? {
        return null
    }

    override fun getAccountRemovalAllowed(response: AccountAuthenticatorResponse, account: android.accounts.Account): Bundle {
        val removable = !TextUtils.equals(account.name, AccountUtil.ACCOUNT_DEFAULT)
        val rval = Bundle()
        rval.putBoolean(AccountManager.KEY_BOOLEAN_RESULT, removable)
        return rval
    }

    override fun getAuthToken(response: AccountAuthenticatorResponse, account: android.accounts.Account, authTokenType: String, options: Bundle): Bundle {
        val accountManager = AccountManager.get(mContext)

        var authToken = accountManager.peekAuthToken(account, authTokenType)

        if (TextUtils.isEmpty(authToken)) {
            authToken = dataManager.loginSync(account.name, accountManager.getPassword(account)).data.token
        }

        if (!TextUtils.isEmpty(authToken)) {
            val result = Bundle()
            result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name)
            result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type)
            result.putString(AccountManager.KEY_AUTHTOKEN, authToken)
            return result
        }
        val intent = Intent(mContext, LoginActivity::class.java)
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)
        intent.putExtra(KEY_ACCOUNT_TYPE, account.type)
        intent.putExtra(KEY_TOKEN_TYPE, authTokenType)

        val retBundle = Bundle()
        retBundle.putParcelable(AccountManager.KEY_INTENT, intent)
        return retBundle
    }

    override fun getAuthTokenLabel(authTokenType: String): String = authTokenType

    override fun updateCredentials(response: AccountAuthenticatorResponse, account: android.accounts.Account, authTokenType: String, options: Bundle): Bundle? {
        return null
    }

    override fun hasFeatures(response: AccountAuthenticatorResponse, account: android.accounts.Account, features: Array<String>): Bundle? {
        return null
    }

}