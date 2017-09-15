package net.oneread.oneread.debug


import android.support.annotation.CallSuper
import android.text.TextUtils
import android.view.animation.Animation

import net.oneread.oneread.BuildConfig


open class TracingAnimationListener(traceName: String) : Animation.AnimationListener {
    private var traceName: String

    init {
        if (TextUtils.isEmpty(traceName)) {
            if (BuildConfig.DEBUG) {
                throw RuntimeException("TracingAnimationListener name is empty")
            } else {
                this.traceName = javaClass.name
            }
        } else {
            this.traceName = traceName
        }
    }

    @CallSuper
    override fun onAnimationStart(animation: Animation) {
        AnimationTracer.start(traceName)
    }

    @CallSuper
    override fun onAnimationEnd(animation: Animation) {
        AnimationTracer.stop(traceName).print()
    }

    override fun onAnimationRepeat(animation: Animation) {

    }
}
