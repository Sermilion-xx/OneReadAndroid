package net.oneread.oneread.injection.module

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides
import net.oneread.oneread.injection.ActivityContext
import net.oneread.oneread.injection.PerActivity

@Module
open class ActivityModule(protected val activity: Activity) {

    @Provides
    @PerActivity
    internal fun provideActivity(): Activity {
        return activity
    }

    @Provides
    @PerActivity
    @ActivityContext
    internal fun providesContext(): Context {
        return activity
    }

}
