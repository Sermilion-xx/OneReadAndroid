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
        private val PREF_DEVICE_HAS_SOFT_NAV_BAR = "net.oneread.onereadandroid.device_has_software_keys"
        private val PREF_CTX_FRONTPAGE_SETTINGS_USER = "net.oneread.onereadandroid.settings.%s"
        private val ANONYMOUS_USERNAME = "anonymous"
        private var INSTANCE: OneReadSettings? = null

        private var username: String? = null
        private lateinit var sharedPrefs: SharedPreferences


        fun getInstance(): OneReadSettings {
            val activeSession = SessionManager.INSTANCE.getActiveSession()
            val currentUser = if (activeSession != null && activeSession.isAnonymous())
                ANONYMOUS_USERNAME
            else
                activeSession?.email
            if (!TextUtils.equals(username, currentUser) || INSTANCE == null) {
                username = currentUser
                INSTANCE = OneReadSettings(OneReadApplication.INSTANCE)
            }
            return INSTANCE as OneReadSettings
        }
    }


    init {
        sharedPrefs = context.getSharedPreferences(
                String.format(PREF_CTX_FRONTPAGE_SETTINGS_USER, username),
                Context.MODE_PRIVATE
        )
    }

    fun hasSoftNavBar(): Boolean {
        return sharedPrefs.getBoolean(PREF_DEVICE_HAS_SOFT_NAV_BAR, true)
    }

    fun setHasSoftNavBar(deviceHasSoftwareKeys: Boolean) {
        sharedPrefs
                .edit()
                .putBoolean(PREF_DEVICE_HAS_SOFT_NAV_BAR, deviceHasSoftwareKeys)
                .apply()
    }

}
