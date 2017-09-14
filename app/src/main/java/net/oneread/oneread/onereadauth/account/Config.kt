package net.oneread.oneread.onereadauth.account

import android.net.Uri
import android.os.Build
import android.text.TextUtils
import net.oneread.oneread.BuildConfig
import net.oneread.oneread.R
import net.oneread.oneread.util.Util

/**
 * Created by sermilion on 9/10/17.
 */
object Config {

    val BASE_URI = Uri.parse("https://www.reddit.com")
    val userAgent = Util.getString(R.string.user_agent,
            BuildConfig.VERSION_NAME,
            BuildConfig.VERSION_CODE,
            Build.VERSION.RELEASE
    )

    var clientId: String? = null
    var clientSecret: String? = null
    var redirectUri: String? = null
    var sessionId: String = ""
    set(value) {
        if (!TextUtils.isEmpty(value)) {
            field = value
            val sessionIdComponents = TextUtils.split(field, "\\.")
            if (sessionIdComponents.size == 4) {
                Config.sessionIdFirstComponent = sessionIdComponents[0]
            } else {
                // fallback to full server session id if we were unable to parse the first component
                Config.sessionIdFirstComponent = value
            }
        }
    }

    var deviceId: String = ""
    var loId: String? = null
    var externalInstallId: String? = null
    var googleAdId: String? = null
    var amazonAdId: String? = null
    var externalInstallIdTs: Long = 0
    // allow null because some users have an unknown install time
    var installTs: Long? = null

    // loId and sessionId are in this format: id.version.created.cryptoblob (from server)
    private var loIdComponents: Array<String>? = null
    var sessionIdFirstComponent: String? = null
        private set


    fun getLoIdComponents(): Array<String>? {
        if (loIdComponents == null && !TextUtils.isEmpty(Config.loId)) {
            loIdComponents = TextUtils.split(Config.loId, "\\.")
        }
        return loIdComponents
    }



}