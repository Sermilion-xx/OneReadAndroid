package net.oneread.oneread.util

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by sermilion on 9/10/17.
 */
/**
 * Simplifies exposing Rx Java schedulers. No need to remember if the scheduler is stored
 * in Schedulers or AndroidSchedulers.
 */
object SchedulerProvider {
    fun computation(): Scheduler = Schedulers.computation()

    fun io(): Scheduler = Schedulers.io()

    fun ui(): Scheduler = AndroidSchedulers.mainThread()
}