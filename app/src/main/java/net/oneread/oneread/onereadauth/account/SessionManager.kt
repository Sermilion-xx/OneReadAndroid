package net.oneread.oneread.onereadauth.account

import android.accounts.*
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.text.TextUtils
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import net.oneread.oneread.OneReadApplication
import net.oneread.oneread.onereadauth.models.Scope
import net.oneread.oneread.onereadauth.models.Session
import net.oneread.oneread.util.SchedulerProvider
import net.oneread.oneread.util.TokenException
import timber.log.Timber
import java.io.IOException
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by sermilion on 9/10/17.
 */

class SessionManager {

    companion object {
        lateinit var INSTANCE: SessionManager
        private set
        // TODO: more event types
        val SESSION_CHANGED = "net.oneread.SESSION_CHANGED"

        val PREF_NAME_AUTH = "net.oneread.auth"
        val PREF_KEY_USERNAME = "email"
        val PREF_KEY_ACCOUNT_TYPE = "account_type"
        val PREF_KEY_TOKEN = "token"
        val PREF_KEY_TOKEN_EXPIRATION = "token_expiration"
        val EXTRA_IS_SIGN_UP = "net.oneread.extra.is_sign_up"
        val ARG_IS_REGISTRATION = "net.oneread.is_registration"

        private val TOKEN_EXPIRED: Long = -1
        private val REQUEST_SESSION = 42

        fun init() {
            INSTANCE = SessionManager()
        }
    }

    // known sessions.
    private val sessions = ConcurrentHashMap<Session.SessionId, Session>()
    private val ANONYMOUS_SESSION = makeSession(username = "",
            accountType = AccountUtil.ACCOUNT_TYPE,
            token = "", expiration = TOKEN_EXPIRED)

    private var activeSession: Session? = null

    private var isSignUp: Boolean = false

    private val sessionChangedSubject: PublishSubject<Session> = PublishSubject.create()
    private val tokenSubject = PublishSubject.create<String>()

    init {
        // TODO: Move this into a RxEventBus
        restoreActiveSession()
    }

    fun setActiveSession(session: Session) {
        activeSession = session
        persistActiveSession()
    }

    private fun makeSession(username: String, accountType: String, token: String, expiration: Long): Session {
        val id = Session.SessionId.of(username, accountType)
        var session: Session? = sessions[id]
        if (session == null) {
            session = Session(username, accountType, token)
            val existing = sessions.putIfAbsent(id, session)
            if (existing != null) {
                session = existing
            }
        }
        if (expiration != TOKEN_EXPIRED) {
            session.updateToken(token, expiration)
        }
        return session
    }

    private fun persistActiveSession() {
        if (activeSession != null) {
            persistSession(activeSession as Session)
        }
    }

    private fun persistSession(session: Session) {
        if (!session.isTokenInvalid()) {
            tokenSubject.onNext(session.token)
        }
        OneReadApplication.INSTANCE.getSharedPreferences(PREF_NAME_AUTH, Context.MODE_PRIVATE)
                .edit()
                .putString(PREF_KEY_USERNAME, session.email)
                .putString(PREF_KEY_ACCOUNT_TYPE, session.accountType)
                .putString(PREF_KEY_TOKEN, session.token)
                .putLong(PREF_KEY_TOKEN_EXPIRATION, session.expiration)
                .apply()
    }

    fun restoreActiveSession() {
        if (activeSession != null) {
            return
        }
        val prefs = OneReadApplication.INSTANCE.getSharedPreferences(PREF_NAME_AUTH, Context.MODE_PRIVATE)
        val username = prefs.getString(PREF_KEY_USERNAME, null)
        val accountType = prefs.getString(PREF_KEY_ACCOUNT_TYPE, AccountUtil.ACCOUNT_TYPE)
        val token = prefs.getString(PREF_KEY_TOKEN, null)
        val expiration = prefs.getLong(PREF_KEY_TOKEN_EXPIRATION, TOKEN_EXPIRED)
        if (username != null) {
            setActiveSession(makeSession(username, accountType, token, expiration))
            if (!accountExists(username)) {
                clearCurrent()
            }
        } else {
            setActiveSession(ANONYMOUS_SESSION)
        }
    }

    private fun accountExists(username: String): Boolean {
        val manager = AccountManager.get(OneReadApplication.INSTANCE)
        val accounts = manager.getAccountsByType(AccountUtil.ACCOUNT_TYPE)
        return accounts.any { it.name.contains(username) }
    }

    private fun clearSession() {
        OneReadApplication.INSTANCE.getSharedPreferences(PREF_NAME_AUTH, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply()
    }

    fun getContext(): Context = OneReadApplication.INSTANCE

    fun loginToOneRead(activity: Activity) {
        authenticationPrompt(activity, Scope.ALL_SCOPE, false)
    }

    fun signupForOneRead(activity: Activity) {
        authenticationPrompt(activity, Scope.ALL_SCOPE, true)
    }

    private fun authenticationPrompt(activity: Activity, scope: Scope, signup: Boolean) {
        val accounts = AccountUtil.getAccounts(activity.applicationContext)
        val accountTypes = arrayOf(AccountUtil.ACCOUNT_TYPE)
        Timber.d("current user = %s type = %s", activeSession?.email, activeSession?.accountType)

        var current: Account? =
                null
        if (!(activeSession as Session).isAnonymous()) {
            current = Account(activeSession?.email, activeSession?.accountType)
        }

        val args = Bundle()
        args.putBoolean(ARG_IS_REGISTRATION, signup)

        val chooser: Intent

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            chooser = AccountManager.newChooseAccountIntent(
                    current, /* ORAccount to show as selected by default. Can be null (no default) */
                    accounts, /* List of accounts to choose from. If specified, the choices will be limited to these */
                    accountTypes, null, /* Description override text */
                    scope.toString(), null, /* String-array of required features */
                    args /* options Bundle to pass to addAccount */
            )/* An array of account types. Filters the list of accounts and the types of accounts that can be added *//* Auth token type string */
        } else {
            chooser = AccountManager.newChooseAccountIntent(
                    current, /* ORAccount to show as selected by default. Can be null (no default) */
                    accounts, /* List of accounts to choose from. If specified, the choices will be limited to these */
                    accountTypes, /* An array of account types. Filters the list of accounts and the types of accounts that can be added */
                    true, null, /* Description override text */
                    scope.toString(), null, /* String-array of required features */
                    args /* options Bundle to pass to addAccount */
            )
        }

        activity.startActivityForResult(chooser, REQUEST_SESSION)
    }

    private fun broadcastSessionChange(silent: Boolean) {
        if (!silent) {
            val intent = Intent(SESSION_CHANGED)
            intent.putExtra(EXTRA_IS_SIGN_UP, isSignUp)
            LocalBroadcastManager.getInstance(OneReadApplication.INSTANCE).sendBroadcast(intent)
        }
        isSignUp = false
        if (!silent) {
            sessionChangedSubject.onNext(activeSession as Session)
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        Timber.d("got result")

        if (requestCode != REQUEST_SESSION) {
            return
        }

        if (resultCode != Activity.RESULT_OK) {
            return
        }

        val name = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME)
        val type = data.getStringExtra(AccountManager.KEY_ACCOUNT_TYPE)
        Timber.d("result: name=%s type=%s", name, type)

        switchAccount(name, type, false)

        Timber.d("onActivityResult called")
    }

    fun switchAccount(name: String, type: String, silent: Boolean) {
        val newActive = makeSession(name, type, "", TOKEN_EXPIRED)
        setActiveSession(newActive)
        broadcastSessionChange(silent)
    }

    fun invalidateToken(session: Session) {
        val manager = AccountManager.get(OneReadApplication.INSTANCE)

        // if this is the current account, take certain precautions
        if (TextUtils.equals(session.email, activeSession?.email)) {
            OneReadApplication.INSTANCE.getSharedPreferences(PREF_NAME_AUTH, Context.MODE_PRIVATE)
                    .edit()
                    .remove(PREF_KEY_TOKEN)
                    .apply()
        }

        manager.invalidateAuthToken(session.accountType, session.token)
        updateSessionToken(session, Session.INVALID_TOKEN, TOKEN_EXPIRED)

        Timber.d("invalidateToken: %s(%s) busted %s", session.email, session.accountType, session.token)
    }

    // update a session token and persist it if the session is active.
    private fun updateSessionToken(session: Session, newToken: String, expiration: Long) {
        session.updateToken(newToken, expiration)
        if (session.email.isEmpty() && ANONYMOUS_SESSION == activeSession) {
            persistSession(session)
        } else if (TextUtils.equals(session.email, activeSession?.email)) {
            persistSession(session)
        }
    }

    private fun updateSessionToken(session: Session, bundle: Bundle?) {
        if (bundle == null) {
            return
        }
        val token = bundle.getString(AccountManager.KEY_AUTHTOKEN)
        val expiration = bundle.getLong(AccountAuthenticator.USER_DATA_EXPIRATION)
        Timber.d("Got new token: %s", session.token)
        updateSessionToken(session, token, expiration)
    }

    fun getActiveSession(): Session? = activeSession

    fun getActiveAccount(): net.oneread.oneread.onereadauth.models.ORAccount? =
            AccountStorage.INSTANCE[getActiveSession()?.email]

    fun getSession(account: Account): Session {
        if (TextUtils.equals(account.name, AccountUtil.ACCOUNT_DEFAULT)) {
            return ANONYMOUS_SESSION
        }

        val bundle = requestAccountToken(account)
        val token = bundle.getString(AccountManager.KEY_AUTHTOKEN)
        val expiration = bundle.getLong(AccountAuthenticator.USER_DATA_EXPIRATION, TOKEN_EXPIRED)
        Timber.d("Token from account manager: %s", token)

        return makeSession(account.name, account.type, token, expiration)
    }

    fun ensureActiveSessionToken() {
        if (activeSession == null) {
            Timber.w("No active session")
            return
        } else if (activeSession!!.isTokenInvalid()) {
            try {
                requestTokenSynchronous(activeSession as Session)
            } catch (tokenRotationError: TokenException) {
                Timber.e(tokenRotationError, "Failed to get token for session: %s", activeSession?.email)
            }

        }
    }

    fun getAnonymousSession(): Session = ANONYMOUS_SESSION

    fun refresh(session: Session): Session {
        var refresh: Session? = sessions.putIfAbsent(session.sessionId, session)
        if (refresh == null) {
            refresh = sessions[session.sessionId]
        }
        return refresh as Session
    }

    private fun clearCurrent() {
        if (activeSession != null && (activeSession as Session).isAnonymous()) {
            clearSession()
            clearSession(activeSession as Session)
            activeSession = ANONYMOUS_SESSION
        }
    }

    private fun clearSession(session: Session) {
        sessions.remove(session.sessionId)
    }

    fun endCurrent() {
        clearCurrent()
        broadcastSessionChange(false)
    }

    fun endIfActive(account: Account): Boolean {
        if (activeSession == null) {
            return false
        }

        val sessionId = Session.SessionId.of(account.name, account.type)
        if (sessionId == activeSession?.sessionId) {
            endCurrent()
            return true
        }

        return false
    }

    @Throws(TokenException::class)
    private fun requestTokenAnonymousSynchronous() {
        val bundle = requestAccountToken(Account(AccountUtil.ACCOUNT_DEFAULT, AccountUtil.ACCOUNT_TYPE))
        updateSessionToken(ANONYMOUS_SESSION, bundle)
    }

    @Throws(TokenException::class)
    fun requestTokenSynchronous(session: Session) {
        Timber.d("requestTokenSynchronous")

        Timber.d("account type: %s", session.accountType)
        if (session.isAnonymous()) {
            requestTokenAnonymousSynchronous()
        } else {
            val bundle = requestAccountToken(Account(session.email, session.accountType))
            updateSessionToken(session, bundle)
        }
    }

    @Throws(TokenException::class)
    fun requestTokenAsync(session: Session) {
        // invalidate the token in the account manager, but not in the session itself - we'll still be able to use it while we update
        val manager = AccountManager.get(OneReadApplication.INSTANCE)
        manager.invalidateAuthToken(session.accountType, session.token)

        Timber.d("requestTokenAsync")

        Timber.d("account type: %s", session.accountType)
        if (session.isAnonymous()) {
            requestTokenAnonymousAsync()
        } else {
            val account = Account(session.email, session.accountType)
            requestAccountTokenAsync(account, AccountManagerCallback { future ->
                try {
                    updateSessionToken(session, future.result)
                } catch (e: OperationCanceledException) {
                    Timber.e(e, "Failed to update token asynchronously")
                } catch (e: IOException) {
                    Timber.e(e, "Failed to update token asynchronously")
                } catch (e: AuthenticatorException) {
                    Timber.e(e, "Failed to update token asynchronously")
                }
            })
        }
    }

    @Throws(TokenException::class)
    private fun requestTokenAnonymousAsync() {
        val account = Account(AccountUtil.ACCOUNT_DEFAULT, AccountUtil.ACCOUNT_TYPE)
        requestAccountTokenAsync(account, AccountManagerCallback { future ->
            try {
                val bundle = future.result
                updateSessionToken(ANONYMOUS_SESSION, bundle)
            } catch (e: OperationCanceledException) {
                Timber.e(e, "Failed to update token asynchronously")
            } catch (e: IOException) {
                Timber.e(e, "Failed to update token asynchronously")
            } catch (e: AuthenticatorException) {
                Timber.e(e, "Failed to update token asynchronously")
            }
        })
    }

    @Throws(TokenException::class)
    private fun requestAccountToken(account: Account): Bundle {
        try {
            return AccountManager.get(OneReadApplication.INSTANCE).getAuthToken(
                    account,
                    Scope.ALL_SCOPE.toString(), null, false, null, null).result ?: throw TokenException("Unable to retrieve. Bundle is null")
        } catch (e: OperationCanceledException) {
            throw TokenException(e)
        } catch (e: IOException) {
            throw TokenException(e)
        } catch (e: AuthenticatorException) {
            throw TokenException(e)
        }

    }

    private fun requestAccountTokenAsync(account: Account, callback: AccountManagerCallback<Bundle>) {
        AccountManager.get(OneReadApplication.INSTANCE).getAuthToken(
                account,
                Scope.ALL_SCOPE.toString(), null, false, callback, null)
    }

    fun notifyIsSignUp() {
        this.isSignUp = true
    }

    fun sessionChangedObservable(): Observable<Session> = sessionChangedSubject


    fun getTokenObservable(): Observable<String> {
        if (activeSession != null &&!(activeSession as Session).isTokenInvalid()) {
            return Observable.just(activeSession?.token)
        } else {
            Observable.fromCallable<Any> {
                try {
                    SessionManager.INSTANCE.requestTokenSynchronous(activeSession as Session)
                } catch (e: TokenException) {
                    Timber.e("requestTokenSynchronous error: " + e)
                }

                activeSession?.token
            }.subscribeOn(SchedulerProvider.io())
                    .subscribe({ _ -> }
                    ) { error -> Timber.e("Refresh token error:" + error) }
        }

        return tokenSubject
    }

}
