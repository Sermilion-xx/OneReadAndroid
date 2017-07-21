package net.oneread.oneread.injection.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import net.oneread.oneread.injection.ApplicationContext
import javax.inject.Singleton

/**
 * Provide application-level dependencies.
 */
@Module
class ApplicationModule(val application: Application) {

    @Provides
    @Singleton
    internal fun provideApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    @ApplicationContext
    internal fun provideContext(): Context {
        return application
    }
}
