package net.oneread.oneread.debug


import android.animation.Animator
import android.support.annotation.CallSuper
import android.text.TextUtils

import net.oneread.oneread.BuildConfig

open class TracingAnimatorListener(traceName: String) : Animator.AnimatorListener {
    private var traceName: String

    init {
        if (TextUtils.isEmpty(traceName)) {
            if (BuildConfig.DEBUG) {
                throw RuntimeException("attempted to start a TracingAnimatorListener with an empty trace name")
            } else {
                this.traceName = javaClass.name
            }
        } else {
            this.traceName = traceName
        }
    }

    @CallSuper
    override fun onAnimationStart(animation: Animator) {
        AnimationTracer.start(traceName)
    }

    @CallSuper
    override fun onAnimationEnd(animation: Animator) {
        AnimationTracer.stop(traceName).print()
    }

    override fun onAnimationCancel(animation: Animator) {

    }

    override fun onAnimationRepeat(animation: Animator) {

    }
}
