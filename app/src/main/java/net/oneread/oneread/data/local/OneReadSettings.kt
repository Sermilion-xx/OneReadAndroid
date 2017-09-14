package net.oneread.oneread.data.local

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import net.oneread.oneread.OneReadApplication
import net.oneread.oneread.onereadauth.account.SessionManager

/**
 * Created by sermilion on 9/10/17.
 */
class OneReadSettings private constructor(context: Context) {

    companion object {
        private val TAG = OneReadSettings::class.java.simpleName
        private val PREF_DEVICE_HAS_SOFTWARE_KEYS = "net.oneread.onereadandroid.device_has_software_keys"
        private val PREF_CTX_FRONTPAGE_SETTINGS_USER = "net.oneread.onereadandroid.settings.%s"
        private val ANONYMOUS_USERNAME = "anonymous"
        private var instance: OneReadSettings? = null
    }

    private var username: String? = null
    private var sharedPrefs: SharedPreferences

    fun getInstance(): OneReadSettings {
        val activeSession = SessionManager.INSTANCE.getActiveSession()
        val currentUser = if (activeSession != null && activeSession.isAnonymous())
            ANONYMOUS_USERNAME
        else
            activeSession?.email
        if (!TextUtils.equals(username, currentUser) || instance == null) {
            username = currentUser
            instance = OneReadSettings(OneReadApplication.INSTANCE)
        }
        return instance as OneReadSettings
    }

    init {
        sharedPrefs = context.getSharedPreferences(
                String.format(PREF_CTX_FRONTPAGE_SETTINGS_USER, username),
                Context.MODE_PRIVATE
        )
    }
}
