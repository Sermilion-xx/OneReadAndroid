package net.oneread.oneread.injection.component

import net.oneread.oneread.injection.module.ActivityModule
import dagger.Subcomponent
import net.oneread.oneread.injection.PerActivity

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
//    fun inject(mainActivity: MainActivity)
}
