package net.oneread.oneread.debug


import android.support.annotation.UiThread
import android.view.Choreographer

import java.util.HashMap

import timber.log.Timber

object AnimationTracer {
    private lateinit var uiChoreographer: Choreographer
    private var runningTraces: MutableMap<String, TraceLog> = HashMap()

    @UiThread
    fun init() {
        uiChoreographer = Choreographer.getInstance()
        runningTraces = HashMap()
    }

    fun start(traceName: String) {
        if (uiChoreographer == null) {
            return
        }
        runningTraces.put(traceName, TraceLog(uiChoreographer, traceName))
    }

    fun stop(traceName: String): TraceLog {
        if (uiChoreographer == null) {
            return TraceLog.DUMMY
        }
        val trace = runningTraces[traceName] ?: return TraceLog.DUMMY
        trace.durationInMillis = System.currentTimeMillis() - trace.traceStart
        uiChoreographer.removeFrameCallback(trace.frameCallback)
        runningTraces.remove(traceName)
        return trace
    }


    class TraceLog {
        var frameCallback: Choreographer.FrameCallback? = null
        var traceStart: Long = 0
        var frameCount: Int = 0
        var durationInMillis: Long = 0
        var name: String = ""

        private constructor() {}

        constructor(choreographer: Choreographer?, name: String) {
            this.name = name
            this.traceStart = System.currentTimeMillis()
            frameCallback = object : Choreographer.FrameCallback {
                override fun doFrame(frameTimeNanos: Long) {
                    choreographer?.postFrameCallback(this)
                    frameCount++
                }
            }
            choreographer?.postFrameCallback(frameCallback)
        }

        private fun calcFps(): Int {
            return (frameCount / (durationInMillis / 1000f)).toInt()
        }

        override fun toString(): String {
            return if (frameCallback == null) {
                ""
            } else StringBuilder(21)
                    .append("Trace [").append(name).append("]: ")
                    .append(calcFps()).append("fps in ")
                    .append(durationInMillis).append("ms")
                    .toString()
        }

        fun print() {
            Timber.d(toString())
        }

        companion object {
            val DUMMY = TraceLog()
        }
    }
}
