package net.oneread.oneread.injection.module;

@kotlin.Metadata(mv = {1, 1, 6}, bv = {1, 0, 1}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\r\u0010\u0007\u001a\u00020\u0003H\u0001\u00a2\u0006\u0002\b\bJ\r\u0010\t\u001a\u00020\nH\u0001\u00a2\u0006\u0002\b\u000bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\f"}, d2 = {"Lnet/oneread/oneread/injection/module/ActivityModule;", "", "activity", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "getActivity", "()Landroid/app/Activity;", "provideActivity", "provideActivity$app_debug", "providesContext", "Landroid/content/Context;", "providesContext$app_debug", "app_debug"})
@dagger.Module()
public class ActivityModule {
    @org.jetbrains.annotations.NotNull()
    private final android.app.Activity activity = null;
    
    @org.jetbrains.annotations.NotNull()
    @net.oneread.oneread.injection.PerActivity()
    @dagger.Provides()
    public final android.app.Activity provideActivity$app_debug() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @net.oneread.oneread.injection.ActivityContext()
    @net.oneread.oneread.injection.PerActivity()
    @dagger.Provides()
    public final android.content.Context providesContext$app_debug() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    protected final android.app.Activity getActivity() {
        return null;
    }
    
    public ActivityModule(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
        super();
    }
}