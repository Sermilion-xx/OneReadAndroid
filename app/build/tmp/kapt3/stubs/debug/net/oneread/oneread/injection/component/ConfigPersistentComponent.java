package net.oneread.oneread.injection.component;

@kotlin.Metadata(mv = {1, 1, 6}, bv = {1, 0, 1}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lnet/oneread/oneread/injection/component/ConfigPersistentComponent;", "", "activityComponent", "Lnet/oneread/oneread/injection/component/ActivityComponent;", "activityModule", "Lnet/oneread/oneread/injection/module/ActivityModule;", "app_debug"})
@dagger.Component(dependencies = {net.oneread.oneread.injection.component.ApplicationComponent.class})
@net.oneread.oneread.injection.ConfigPersistent()
public abstract interface ConfigPersistentComponent {
    
    @org.jetbrains.annotations.NotNull()
    public abstract net.oneread.oneread.injection.component.ActivityComponent activityComponent(@org.jetbrains.annotations.NotNull()
    net.oneread.oneread.injection.module.ActivityModule activityModule);
}