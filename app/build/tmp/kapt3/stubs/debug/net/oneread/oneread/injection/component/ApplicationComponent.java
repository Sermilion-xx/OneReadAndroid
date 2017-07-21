package net.oneread.oneread.injection.component;

@kotlin.Metadata(mv = {1, 1, 6}, bv = {1, 0, 1}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H\'J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&\u00a8\u0006\n"}, d2 = {"Lnet/oneread/oneread/injection/component/ApplicationComponent;", "", "application", "Landroid/app/Application;", "context", "Landroid/content/Context;", "dataManager", "Lnet/oneread/oneread/data/DataManager;", "oneAccountService", "Lnet/oneread/oneread/data/remote/OneReadService;", "app_debug"})
@dagger.Component(modules = {net.oneread.oneread.injection.module.ApplicationModule.class, net.oneread.oneread.injection.module.DataModule.class})
@javax.inject.Singleton()
public abstract interface ApplicationComponent {
    
    @org.jetbrains.annotations.NotNull()
    @net.oneread.oneread.injection.ApplicationContext()
    public abstract android.content.Context context();
    
    @org.jetbrains.annotations.NotNull()
    public abstract android.app.Application application();
    
    @org.jetbrains.annotations.NotNull()
    public abstract net.oneread.oneread.data.remote.OneReadService oneAccountService();
    
    @org.jetbrains.annotations.NotNull()
    public abstract net.oneread.oneread.data.DataManager dataManager();
}