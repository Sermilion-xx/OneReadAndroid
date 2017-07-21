package net.oneread.oneread.ui.base;

@kotlin.Metadata(mv = {1, 1, 6}, bv = {1, 0, 1}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\u000eB\u0005\u00a2\u0006\u0002\u0010\u0004J\u0015\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u00020\u000bH\u0016R\u0012\u0010\u0005\u001a\u0004\u0018\u00018\u0000X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0006R\u0011\u0010\u0007\u001a\u00028\u00008F\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\t\u00a8\u0006\u000f"}, d2 = {"Lnet/oneread/oneread/ui/base/BasePresenter;", "T", "Lnet/oneread/oneread/ui/base/MvpView;", "Lnet/oneread/oneread/ui/base/Presenter;", "()V", "_view", "Lnet/oneread/oneread/ui/base/MvpView;", "view", "getView", "()Lnet/oneread/oneread/ui/base/MvpView;", "attachView", "", "(Lnet/oneread/oneread/ui/base/MvpView;)V", "detachView", "MvpViewNotAttachedException", "app_debug"})
public class BasePresenter<T extends net.oneread.oneread.ui.base.MvpView> implements net.oneread.oneread.ui.base.Presenter<T> {
    private T _view;
    
    @org.jetbrains.annotations.NotNull()
    public final T getView() {
        return null;
    }
    
    @java.lang.Override()
    public void attachView(@org.jetbrains.annotations.NotNull()
    T view) {
    }
    
    @java.lang.Override()
    public void detachView() {
    }
    
    public BasePresenter() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 6}, bv = {1, 0, 1}, k = 1, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003\u00a8\u0006\u0004"}, d2 = {"Lnet/oneread/oneread/ui/base/BasePresenter$MvpViewNotAttachedException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "()V", "app_debug"})
    public static final class MvpViewNotAttachedException extends java.lang.RuntimeException {
        
        public MvpViewNotAttachedException() {
            super();
        }
    }
}