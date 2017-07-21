package net.oneread.oneread

import android.app.Application
import android.support.annotation.VisibleForTesting
import timber.log.Timber
import net.oneread.oneread.injection.component.ApplicationComponent
import net.oneread.oneread.injection.module.ApplicationModule
import net.oneread.oneread.injection.component.DaggerApplicationComponent

open class OneApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        initDaggerComponent()
    }

    @VisibleForTesting
    fun initDaggerComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    fun setComponent(component: ApplicationComponent) {
        this.applicationComponent = component
    }

    companion object {
        private lateinit var INSTANCE: OneApplication
        fun getInstance(): OneApplication {
            return INSTANCE
        }
    }
}
