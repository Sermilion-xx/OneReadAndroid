package net.oneread.oneread.injection.component;

@kotlin.Metadata(mv = {1, 1, 6}, bv = {1, 0, 1}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lnet/oneread/oneread/injection/component/ActivityComponent;", "", "inject", "", "regActivity", "Lnet/oneread/oneread/ui/registration/RegActivity;", "app_debug"})
@dagger.Subcomponent(modules = {net.oneread.oneread.injection.module.ActivityModule.class})
@net.oneread.oneread.injection.PerActivity()
public abstract interface ActivityComponent {
    
    public abstract void inject(@org.jetbrains.annotations.NotNull()
    net.oneread.oneread.ui.registration.RegActivity regActivity);
}