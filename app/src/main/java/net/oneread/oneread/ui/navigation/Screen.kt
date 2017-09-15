package net.oneread.oneread.ui.navigation

import android.os.Bundle
import android.support.annotation.IntDef
import android.view.MenuItem
import com.bluelinelabs.conductor.Controller
import net.oneread.oneread.util.Util


/**
 * Created by sermilion on 9/14/17.
 */
abstract class Screen(bundle: Bundle) : Controller(bundle) {

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(SAME_AS_CURRENT.toLong(), FULL_SCREEN.toLong())
    internal annotation class ScreenPosition

    companion object {
        const val SAME_AS_CURRENT = 1
        const val FULL_SCREEN = 2
        private val screenLifecycleListener = ScreenLifecycleListener()
    }

    init {
        addLifecycleListener(screenLifecycleListener)
    }

    protected abstract fun init()

    protected abstract fun postViewDestroyed()

    fun getParentScreen(): Screen = parentController as Screen

    fun setTargetScreen(target: Screen) {
        targetController = target
    }

    fun getTargetScreen(): Screen = targetController as Screen

    fun canGoBack(): Boolean = router.backstackSize > 1

    protected fun isValidTargetScreen(screen: Screen): Boolean = true

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            android.R.id.home -> {
                navigateBack()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @ScreenPosition
    fun getDefaultScreenPosition() = SAME_AS_CURRENT

    protected fun navigateAway() {
        Util.hideKeyboard(activity!!.currentFocus)
        Routing.navigateAway(this)
    }

    protected fun navigateBack() {
        Util.hideKeyboard(activity!!.currentFocus)
        Routing.navigateAway(this, true)
    }

    /**
     * Lifecycle method that gets called after all animations bringing this screen into view
     * have finished running
     */
    fun onAnimatedInWindow() {
        childRouters
                .flatMap { it.backstack }
                .forEach { (it.controller() as Screen).onAnimatedInWindow() }
    }

    private class ScreenLifecycleListener : Controller.LifecycleListener() {

        override fun preCreateView(controller: Controller) {
            if (controller is Screen) {
                controller.init()
            }
        }

        override fun onRestoreInstanceState(controller: Controller, savedInstanceState: Bundle) {
            (controller as? Screen)?.init()
            super.onRestoreInstanceState(controller, savedInstanceState)
        }

        override fun postDestroyView(controller: Controller) {
            (controller as? Screen)?.postViewDestroyed()
        }
    }

}