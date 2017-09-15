package net.oneread.oneread.ui.animation

import android.animation.Animator
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.DecelerateInterpolator
import android.view.animation.TranslateAnimation

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler

import net.oneread.oneread.R
import net.oneread.oneread.debug.TracingAnimationListener
import net.oneread.oneread.ui.navigation.Screen
import net.oneread.oneread.util.Util


class VerticalFadeInChangeHandler private constructor(duration: Long, private var removesFromViewWhenPushed: Boolean) : ControllerChangeHandler() {

    var animationDuration: Long = 0
        private set
    private var canceled: Boolean = false
    private var completed: Boolean = false

    private var destinationScreen: Screen? = null

    constructor() : this(DEFAULT_ANIMATION_DURATION, true) {}

    constructor(destination: Screen?) : this(DEFAULT_ANIMATION_DURATION, true) {
        destinationScreen = destination
    }

    constructor(destination: Screen, removesFromViewOnPush: Boolean) : this(DEFAULT_ANIMATION_DURATION, removesFromViewOnPush) {
        destinationScreen = destination
    }

    init {
        animationDuration = duration
    }

    override fun saveToBundle(bundle: Bundle) {
        super.saveToBundle(bundle)
        bundle.putLong(KEY_DURATION, animationDuration)
        bundle.putBoolean(KEY_REMOVES_FROM_ON_PUSH, removesFromViewWhenPushed)
    }

    override fun restoreFromBundle(bundle: Bundle) {
        super.restoreFromBundle(bundle)
        animationDuration = bundle.getLong(KEY_DURATION)
        removesFromViewWhenPushed = bundle.getBoolean(KEY_REMOVES_FROM_ON_PUSH)
    }

    override fun onAbortPush(newHandler: ControllerChangeHandler, newTop: Controller?) {
        super.onAbortPush(newHandler, newTop)
        canceled = true
    }

    override fun removesFromViewOnPush(): Boolean {
        return removesFromViewWhenPushed
    }


    protected fun getAnimation(container: ViewGroup, from: View?, to: View?, isPush: Boolean, toAddedToContainer: Boolean): Animation {
        val alpha: AlphaAnimation
        if (isPush) {
            alpha = AlphaAnimation(0f, 1f)
        } else {
            alpha = AlphaAnimation(1f, 0f)
        }
        alpha.duration = animationDuration

        val translate: TranslateAnimation
        if (isPush) {
            translate = TranslateAnimation(0f, 0f, Util.getDimensionPixelSize(R.dimen.item_image_min_height).toFloat(), 0f)
        } else {
            translate = TranslateAnimation(0f, 0f, 0f, Util.getDimensionPixelSize(R.dimen.item_image_min_height).toFloat())
        }
        translate.duration = animationDuration

        val set = AnimationSet(true)
        set.interpolator = DecelerateInterpolator()
        set.addAnimation(alpha)
        set.addAnimation(translate)

        return set
    }

    protected fun resetFromView(from: View) {
        from.translationY = 0f
    }

    override fun performChange(container: ViewGroup, from: View?, to: View?, isPush: Boolean, changeListener: ControllerChangeHandler.ControllerChangeCompletedListener) {
        var readyToAnimate = true
        val addingToView = to != null && to.parent == null

        if (addingToView) {
            to!!.visibility = View.INVISIBLE
            if (isPush || from == null) {
                container.addView(to)
            } else if (to.parent == null) {
                container.addView(to, container.indexOfChild(from))
            }

            if (to.width <= 0 && to.height <= 0) {
                readyToAnimate = false
                to.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                    override fun onPreDraw(): Boolean {
                        val observer = to.viewTreeObserver
                        if (observer.isAlive) {
                            observer.removeOnPreDrawListener(this)
                        }
                        performAnimation(container, from, to, isPush, addingToView, changeListener)
                        return true
                    }
                })
            }
        }

        if (readyToAnimate) {
            performAnimation(container, from, to, isPush, addingToView, changeListener)
        }
    }

    private fun complete(changeListener: ControllerChangeHandler.ControllerChangeCompletedListener) {
        if (!completed) {
            completed = true
            changeListener.onChangeCompleted()
            if (destinationScreen != null) {
                destinationScreen!!.onAnimatedInWindow()
            }
        }
    }

    private fun performAnimation(container: ViewGroup, from: View?, to: View?, isPush: Boolean, toAddedToContainer: Boolean, changeListener: ControllerChangeHandler.ControllerChangeCompletedListener) {
        if (canceled) {
            complete(changeListener)
            return
        }

        val animation = getAnimation(container, from, to, isPush, toAddedToContainer)

        if (animationDuration > 0) {
            animation.duration = animationDuration
        }

        animation.setAnimationListener(object : TracingAnimationListener("VerticalScreenFade") {
            override fun onAnimationStart(animation: Animation) {
                super.onAnimationStart(animation)
                to!!.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(animation: Animation) {
                to!!.clearAnimation()
                if (!canceled && animation != null) {
                    if (from != null && (!isPush || removesFromViewWhenPushed)) {
                        container.removeView(from)
                    }

                    complete(changeListener)

                    if (isPush && from != null) {
                        resetFromView(from)
                    }
                }
                super.onAnimationEnd(animation)
            }

            override fun onAnimationRepeat(animation: Animation) {
                // these animations never repeat
            }
        })

        to!!.postDelayed({ to.startAnimation(animation) }, 100)
    }

    override fun copy(): ControllerChangeHandler = VerticalFadeInChangeHandler(destinationScreen)

    companion object {

        private val KEY_DURATION = "VerticalFadeInChangeHandler.duration"
        private val KEY_REMOVES_FROM_ON_PUSH = "VerticalFadeInChangeHandler.removesFromViewWhenPushed"

        val DEFAULT_ANIMATION_DURATION: Long = 200
    }
}
