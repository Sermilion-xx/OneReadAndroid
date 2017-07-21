package net.oneread.oneread.ui.base;

@kotlin.Metadata(mv = {1, 1, 6}, bv = {1, 0, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u0000*\n\b\u0000\u0010\u0001 \u0000*\u00020\u00022\u00020\u0003J\u0015\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u0000H&\u00a2\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\u0005H&\u00a8\u0006\t"}, d2 = {"Lnet/oneread/oneread/ui/base/Presenter;", "V", "Lnet/oneread/oneread/ui/base/MvpView;", "", "attachView", "", "view", "(Lnet/oneread/oneread/ui/base/MvpView;)V", "detachView", "app_debug"})
public abstract interface Presenter<V extends net.oneread.oneread.ui.base.MvpView> {
    
    public abstract void attachView(@org.jetbrains.annotations.NotNull()
    V view);
    
    public abstract void detachView();
}