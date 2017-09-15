package net.oneread.oneread.ui.animation

import android.animation.Animator
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import net.oneread.oneread.debug.TracingAnimatorListener
import net.oneread.oneread.ui.navigation.Screen

/**
 * Created by sermilion on 9/14/17.
 */
class ORFadeChangeHandler : FadeChangeHandler {

    private lateinit var destinationScreen: Screen

    constructor() : super()

    constructor(destinationScreen: Screen) : super() {
        this.destinationScreen = destinationScreen
    }

    override fun getAnimator(container: ViewGroup, from: View?, to: View?, isPush: Boolean, toAddedToContainer: Boolean): Animator {
        val animator = super.getAnimator(container, from, to, isPush, toAddedToContainer)
        animator.addListener(object : TracingAnimatorListener("SimpleScreenFade") {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                destinationScreen.onAnimatedInWindow()
            }
        })
        return animator
    }

    override fun copy(): ControllerChangeHandler = ORFadeChangeHandler(destinationScreen)
}
