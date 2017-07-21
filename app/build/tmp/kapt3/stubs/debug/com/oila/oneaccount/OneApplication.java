package com.oila.oneaccount;

@kotlin.Metadata(mv = {1, 1, 6}, bv = {1, 0, 1}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0007J\b\u0010\f\u001a\u00020\u000bH\u0016J\u000e\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u0004R$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t\u00a8\u0006\u0010"}, d2 = {"Lcom/oila/oneaccount/OneApplication;", "Landroid/app/Application;", "()V", "<set-?>", "Lnet/oneread/oneread/injection/component/ApplicationComponent;", "applicationComponent", "getApplicationComponent", "()Lnet/oneread/oneread/injection/component/ApplicationComponent;", "setApplicationComponent", "(Lnet/oneread/oneread/injection/component/ApplicationComponent;)V", "initDaggerComponent", "", "onCreate", "setComponent", "component", "Companion", "app_debug"})
public class OneApplication extends android.app.Application {
    @org.jetbrains.annotations.NotNull()
    private net.oneread.oneread.injection.component.ApplicationComponent applicationComponent;
    private static com.oila.oneaccount.OneApplication INSTANCE;
    public static final com.oila.oneaccount.OneApplication.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public final net.oneread.oneread.injection.component.ApplicationComponent getApplicationComponent() {
        return null;
    }
    
    private final void setApplicationComponent(net.oneread.oneread.injection.component.ApplicationComponent p0) {
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @android.support.annotation.VisibleForTesting()
    public final void initDaggerComponent() {
    }
    
    public final void setComponent(@org.jetbrains.annotations.NotNull()
    net.oneread.oneread.injection.component.ApplicationComponent component) {
    }
    
    public OneApplication() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 6}, bv = {1, 0, 1}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\n"}, d2 = {"Lcom/oila/oneaccount/OneApplication$Companion;", "", "()V", "INSTANCE", "Lcom/oila/oneaccount/OneApplication;", "getINSTANCE", "()Lcom/oila/oneaccount/OneApplication;", "setINSTANCE", "(Lcom/oila/oneaccount/OneApplication;)V", "getInstance", "app_debug"})
    public static final class Companion {
        
        private final com.oila.oneaccount.OneApplication getINSTANCE() {
            return null;
        }
        
        private final void setINSTANCE(com.oila.oneaccount.OneApplication p0) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.oila.oneaccount.OneApplication getInstance() {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}