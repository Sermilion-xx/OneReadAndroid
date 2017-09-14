package net.oneread.oneread.onereadauth.account

import android.content.Context
import net.oneread.oneread.OneReadApplication
import net.oneread.oneread.onereadauth.models.ORAccount
import net.oneread.oneread.onereadauth.models.Session
import java.lang.reflect.Type

/**
 * Created by sermilion on 9/10/17.
 */
class AccountStorage private constructor(context: Context) : BasePersistentStorage<ORAccount>(context) {

    override fun getName(): String = "net.oneread.storage.account"

    override fun getType(): Type  = ORAccount::class.java

    fun persistAccount(session: Session, accountOneRead: ORAccount) {
        synchronized(LOCK) {
            put(session.email, accountOneRead)
        }
    }

    companion object {
        private val LOCK = Any()
        val INSTANCE = AccountStorage(OneReadApplication.INSTANCE)
    }
}
