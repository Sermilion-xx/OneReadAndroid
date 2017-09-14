package net.oneread.oneread.onereadauth.account

import android.accounts.AccountManager.ACTION_AUTHENTICATOR_INTENT
import android.app.Service
import android.content.Intent
import android.os.IBinder



/**
 * Created by sermilion on 9/13/17.
 */

class AccountAuthenticatorService : Service() {

    override fun onBind(intent: Intent): IBinder? =
            if (intent.action == ACTION_AUTHENTICATOR_INTENT) authenticator.iBinder else null

    private val authenticator: AccountAuthenticator
        get() {
            if (AUTHENTICATOR == null)
                AUTHENTICATOR = AccountAuthenticator(this)
            return AUTHENTICATOR as AccountAuthenticator
        }

    companion object {
        private var AUTHENTICATOR: AccountAuthenticator? = null
    }
}