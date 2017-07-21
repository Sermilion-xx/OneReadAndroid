package net.oneread.oneread.injection.module

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import net.oneread.oneread.injection.module.ApiModule
import javax.inject.Singleton

@Module(includes = arrayOf(ApiModule::class))
class DataModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences("mItems", MODE_PRIVATE)
    }
}
