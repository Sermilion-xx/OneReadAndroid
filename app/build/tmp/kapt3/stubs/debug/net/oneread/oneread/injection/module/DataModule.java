package net.oneread.oneread.injection.module;

@kotlin.Metadata(mv = {1, 1, 6}, bv = {1, 0, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u0007"}, d2 = {"Lnet/oneread/oneread/injection/module/DataModule;", "", "()V", "provideSharedPreferences", "Landroid/content/SharedPreferences;", "app", "Landroid/app/Application;", "app_debug"})
@dagger.Module(includes = {net.oneread.oneread.injection.module.ApiModule.class})
public final class DataModule {
    
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Singleton()
    @dagger.Provides()
    public final android.content.SharedPreferences provideSharedPreferences(@org.jetbrains.annotations.NotNull()
    android.app.Application app) {
        return null;
    }
    
    public DataModule() {
        super();
    }
}