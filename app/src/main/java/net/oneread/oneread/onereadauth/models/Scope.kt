package net.oneread.oneread.onereadauth.models

import android.text.TextUtils

/**
 * Created by sermilion on 9/10/17.
 */
class Scope(private val mScopes: Array<String>) {

    override fun toString(): String = TextUtils.join(",", mScopes)

    companion object {
        private val IDENTITY = "identity"
        private val READ = "read"
        private val VOTE = "vote"
        private val REPORT = "report"
        private val SUBMIT = "submit"
        private val EDIT = "edit"
        private val HISTORY = "history"
        private val SAVE = "save"
        private val PRIVATE_MESSAGES = "privatemessages"
        private val SUBSCRIBE = "subscribe"
        private val ACCOUNT = "account"

        private val WILD = "*"


        val SCOPE_SET_IDENTIFY = arrayOf(IDENTITY)

        val SCOPE_SET_ALL = arrayOf(IDENTITY,
                READ,
                VOTE,
                REPORT,
                SUBMIT,
                EDIT,
                HISTORY,
                SAVE,
                PRIVATE_MESSAGES,
                SUBSCRIBE,
                ACCOUNT)

        val SCOPE_SET_WILD = arrayOf(WILD)

        val NULL_SCOPE = Scope(SCOPE_SET_IDENTIFY)
        val IDENTIFY_SCOPE = Scope(SCOPE_SET_IDENTIFY)
        val ALL_SCOPE = Scope(SCOPE_SET_ALL)

        fun fromString(scopes: String): Scope =
                Scope(scopes.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
    }
}