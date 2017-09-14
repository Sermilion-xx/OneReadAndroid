package net.oneread.oneread.ui.widgets

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.os.Parcelable
import android.util.AttributeSet
import android.util.SparseArray
import com.bluelinelabs.conductor.ChangeHandlerFrameLayout


/**
 * View that does not save its state
 *
 * [com.reddit.frontpage.nav.Screen]s automatically save their own state, so no need for it.
 *
 * Solves the issue of state of views with the same ID being applied to all views in a [android.support.v4.view.ViewPager]
 *
 */
class ScreenContainer : ChangeHandlerFrameLayout {

    @JvmOverloads
    constructor(
            context: Context,
            attrs: AttributeSet? = null,
            defStyleAttr: Int = 0)
            : super(context, attrs, defStyleAttr)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
            context: Context,
            attrs: AttributeSet?,
            defStyleAttr: Int,
            defStyleRes: Int)
            : super(context, attrs, defStyleAttr, defStyleRes)
    override fun dispatchSaveInstanceState(container: SparseArray<Parcelable>) {
        dispatchFreezeSelfOnly(container)
    }

    override fun dispatchRestoreInstanceState(container: SparseArray<Parcelable>) {
        dispatchThawSelfOnly(container)
    }
}