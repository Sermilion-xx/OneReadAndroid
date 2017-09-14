package net.oneread.oneread.onereadauth.models

import android.text.TextUtils
import java.io.Serializable

/**
 * Created by sermilion on 9/10/17.
 */
class Session(var email: String, var accountType: String, @Volatile var token: String) : Serializable {

    companion object {
        val INVALID_TOKEN = "invalid-token"
    }

    var sessionId: SessionId
    @Volatile var expiration: Long = 0

    init {
        sessionId = SessionId(email, accountType)
    }

    fun isTokenInvalid(): Boolean =
            expiration < System.currentTimeMillis() || TextUtils.equals(token, INVALID_TOKEN)

    fun isAnonymous(): Boolean = TextUtils.isEmpty(sessionId.username)

    internal fun updateToken(newToken: String, expiration: Long) {
        this.token = newToken
        this.expiration = expiration
    }


    class SessionId internal constructor(val username: String?, private val accountType: String?) : Serializable {

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }
            if (other == null || javaClass != other.javaClass) {
                return false
            }

            val sessionId = other as SessionId?

            return TextUtils.equals(username, sessionId?.username) && TextUtils.equals(accountType, sessionId?.accountType)
        }

        override fun hashCode(): Int {
            var result = username?.hashCode() ?: 0
            result = 31 * result + (accountType?.hashCode() ?: 0)
            return result
        }

        companion object {

            fun of(username: String?, accountType: String): SessionId =
                    SessionId(username, accountType)
        }
    }
}