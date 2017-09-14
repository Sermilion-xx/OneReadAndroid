package net.oneread.oneread.util

import android.support.annotation.StringRes
import net.oneread.oneread.OneReadApplication

/**
 * Created by sermilion on 9/10/17.
 */
object Util {

    fun getString(@StringRes resId: Int, vararg format: Any): String {
        return OneReadApplication.INSTANCE.getString(resId, format)
    }
}