package net.oneread.oneread.injection.component

import net.oneread.oneread.injection.module.ActivityModule
import dagger.Subcomponent
import net.oneread.oneread.injection.PerActivity
import net.oneread.oneread.ui.registration.RegActivity

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(regActivity: RegActivity)
}
