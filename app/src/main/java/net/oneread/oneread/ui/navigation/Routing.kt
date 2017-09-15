package net.oneread.oneread.ui.navigation

import android.app.Activity
import android.content.Context
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import net.oneread.oneread.ui.animation.ORFadeChangeHandler
import net.oneread.oneread.ui.animation.VerticalFadeInChangeHandler
import net.oneread.oneread.util.Util
import timber.log.Timber

/**
 * Created by sermilion on 9/14/17.
 */
object Routing {

    interface Navigatable {
        fun getRootRouter(): Router
        fun getCurrentRouter(): Router
    }

    fun getCurrentScreen(context: Context): Screen? {
        val currentActivity = Util.getActivity(context)

        val router = toNavigateable(currentActivity).getCurrentRouter()
        return getCurrentScreen(router)
    }

    fun getCurrentScreen(router: Router): Screen? {
        return if (!router.hasRootController()) {
            null
        } else router.backstack[router.backstackSize - 1].controller() as Screen
    }

    fun navigateAway(screen: Screen) {
        navigateAway(screen, false)
    }

    fun navigateAway(screen: Screen, popCurrent: Boolean) {
        val activity = screen.activity
        val active = toNavigateable(activity).getCurrentRouter()
        if (!popCurrent && active === rootRouter(screen)) {
            active.popToRoot(defaultChangeHandler(screen))
        } else {
            active.popCurrentController()
        }
    }

    fun navigateTo(context: Context, destination: Screen) {
        val origin = getCurrentScreen(context)
        navigateTo(origin, destination)
    }

    fun navigateTo(origin: Screen?, destination: Screen) {
        navigateTo(origin, destination, destination.getDefaultScreenPosition())
    }

    fun navigateTo(origin: Screen?, destination: Screen, @Screen.ScreenPosition screenPosition: Int) {
        when (screenPosition) {
            Screen.SAME_AS_CURRENT -> {
                val router = toNavigateable(origin?.activity).getCurrentRouter()
                router.pushController(RouterTransaction.with(destination)
                        .pushChangeHandler(defaultChangeHandler(destination))
                        .popChangeHandler(defaultChangeHandler(destination)))
            }
            Screen.FULL_SCREEN -> {
                val router = rootRouter(origin)
                router.pushController(RouterTransaction.with(destination)
                        .pushChangeHandler(fullScreenChangeHandler(destination))
                        .popChangeHandler(defaultChangeHandler(destination)))
            }
            else -> Timber.e("Unknown screen position: %d", screenPosition)
        }
    }

    fun rootRouter(screen: Screen?): Router = Screens.root(screen).router

    fun toNavigateable(activity: Activity?): Navigatable {
        if (activity !is Navigatable) {
            throw IllegalArgumentException("Activity must implement Navigateable")
        }
        return activity
    }

    fun fullScreenChangeHandler(destination: Screen): ControllerChangeHandler =
            VerticalFadeInChangeHandler(destination)

    fun defaultChangeHandler(destination: Screen) =
            ORFadeChangeHandler(destination)

}