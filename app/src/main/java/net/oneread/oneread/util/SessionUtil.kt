package net.oneread.oneread.util

import android.accounts.Account
import android.text.TextUtils
import net.oneread.oneread.onereadauth.account.SessionManager
import net.oneread.oneread.onereadauth.models.Session
import timber.log.Timber


object SessionUtil {

    val ACCOUNT_TYPE = "t2_"

    val isActiveAnonymous: Boolean
        get() {
            val session: Session? = SessionManager.INSTANCE.getActiveSession()
            if (session != null) {
                return session.isAnonymous()
            }
            return false
        }


    fun sessionIsUser(session: Session?, username: String?): Boolean {
        return if (session == null) {
            username == null
        } else username != null && !session.isAnonymous() && username.equals(session.email, ignoreCase = true)
    }

    fun getSession(account: Account, tag: String): Session? {
        var session: Session? = null
        try {
            session = SessionManager.INSTANCE.getSession(account)
        } catch (e: Exception) {
            Timber.e(e, tag)
        }

        return session
    }

    fun logout() {
        SessionManager.INSTANCE.endCurrent()
    }

    val currentUsername: String?
        get() = SessionManager.INSTANCE.getActiveSession()?.email

    fun prependUserTypeIfMissing(userId: String): String? {
        if (TextUtils.isEmpty(userId)) {
            return null
        }
        return if (userId.startsWith(ACCOUNT_TYPE)) {
            userId
        } else {
            ACCOUNT_TYPE + userId
        }
    }
}
