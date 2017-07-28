package net.oneread.oneread.injection.component

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import net.oneread.oneread.injection.module.ApplicationModule
import net.oneread.oneread.injection.module.DataModule
import dagger.Component
import net.oneread.oneread.data.DataManager
import net.oneread.oneread.data.remote.OneReadService
import net.oneread.oneread.injection.ApplicationContext
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, DataModule::class))
interface ApplicationComponent {

//exposing dependencies from ApplicationComponent to components that depend on it
    @ApplicationContext fun context(): Context
    fun application(): Application
    fun oneAccountService(): OneReadService
    fun dataManager(): DataManager
    fun sharedPreferences(): SharedPreferences
}
