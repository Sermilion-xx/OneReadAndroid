package net.oneread.oneread.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.support.annotation.DimenRes
import android.support.annotation.StringRes
import android.view.View
import android.view.inputmethod.InputMethodManager
import net.oneread.oneread.OneReadApplication

/**
 * Created by sermilion on 9/10/17.
 */
object Util {

    fun getActivity(context: Context): Activity {
        var localContext = context
        while (localContext !is Activity && localContext is ContextWrapper) {
            localContext = localContext.baseContext
        }
        return localContext as Activity
    }

    fun getString(@StringRes resId: Int, vararg format: Any): String =
            OneReadApplication.INSTANCE.getString(resId, format)

    fun hideKeyboard(view: View?) {
        if (view == null) {
            return
        }
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun getDimensionPixelOffset(@DimenRes resId: Int): Int =
            OneReadApplication.INSTANCE.resources.getDimensionPixelOffset(resId)

    fun getDimensionPixelSize(@DimenRes resId: Int): Int =
            OneReadApplication.INSTANCE.resources.getDimensionPixelSize(resId)


}