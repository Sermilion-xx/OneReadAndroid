package net.oneread.oneread.onereadauth.account

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import android.os.Build
import android.text.TextUtils
import java.util.*

/**
 * Created by sermilion on 9/10/17.
 */
object AccountUtil {

    val ACCOUNT_TYPE = "User"
    val ACCOUNT_DEFAULT = "OneRead for Android"
    private val DEFAULT_ACCOUNT = Account(ACCOUNT_DEFAULT, ACCOUNT_TYPE)

    internal fun getAccounts(context: Context): ArrayList<Account> {
        val allAccounts = AccountManager.get(context).getAccountsByType(AccountUtil.ACCOUNT_TYPE)
        return allAccounts.filterNotTo(ArrayList()) { TextUtils.equals(it.name, ACCOUNT_DEFAULT) }
    }

    fun getAccountByName(context: Context, name: String): Account? {
        val manager = AccountManager.get(context)
        val accounts = manager.getAccountsByType(ACCOUNT_TYPE)

        for (account in accounts) {
            if (account.name == name) {
                return account
            }
        }

        return null
    }

    /**
     * Adds a default account to the account manager if no such account exists
     * Does nothing if a default account exists
     */
    fun createDefaultAccount(context: Context): Boolean {
        if (getDefaultAccount(context) != null) {
            return false
        }
        val account = Account(
                AccountUtil.ACCOUNT_DEFAULT,
                AccountUtil.ACCOUNT_TYPE
        )
        return AccountManager.get(context).addAccountExplicitly(account, null, null)
    }

    fun removeAccount(context: Context, account: Account): Boolean {
        if (DEFAULT_ACCOUNT == account) {
            return false
        }
        val manager = AccountManager.get(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            manager.removeAccount(account, null, null, null)
        } else {
            manager.removeAccount(account, null, null)
        }
        return true
    }

    fun getDefaultAccount(context: Context): Account? = getAccountByName(context, ACCOUNT_DEFAULT)

    fun isDefaultAccount(account: Account): Boolean =
            TextUtils.equals(account.name, ACCOUNT_DEFAULT)

    fun getTokenExpiration(manager: AccountManager, account: Account): Long {
        val value = manager.getUserData(account, "expiration")
        return if (!TextUtils.isEmpty(value)) java.lang.Long.parseLong(value) else -1
    }

    fun isAccountSuspended(sessionManager: SessionManager): Boolean =
            sessionManager.getActiveAccount() != null && (sessionManager.getActiveAccount() as net.oneread.oneread.onereadauth.models.ORAccount).is_suspended

}