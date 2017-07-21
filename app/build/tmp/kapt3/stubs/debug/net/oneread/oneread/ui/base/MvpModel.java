package net.oneread.oneread.ui.base;

@kotlin.Metadata(mv = {1, 1, 6}, bv = {1, 0, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0006\b\u0001\u0010\u0002 \u0001*\u0006\b\u0002\u0010\u0003 \u00002\u00020\u0004J\b\u0010\u0005\u001a\u00020\u0006H&J\r\u0010\u0007\u001a\u00028\u0001H&\u00a2\u0006\u0002\u0010\bJ\u001b\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\n2\u0006\u0010\u000b\u001a\u00028\u0002H&\u00a2\u0006\u0002\u0010\fJ\u0015\u0010\r\u001a\u00028\u00012\u0006\u0010\u000e\u001a\u00028\u0000H&\u00a2\u0006\u0002\u0010\u000f\u00a8\u0006\u0010"}, d2 = {"Lnet/oneread/oneread/ui/base/MvpModel;", "L", "P", "E", "", "clear", "", "getData", "()Ljava/lang/Object;", "loadData", "Lio/reactivex/Observable;", "params", "(Ljava/lang/Object;)Lio/reactivex/Observable;", "processData", "data", "(Ljava/lang/Object;)Ljava/lang/Object;", "app_debug"})
public abstract interface MvpModel<L extends java.lang.Object, P extends java.lang.Object, E extends java.lang.Object> {
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Observable<L> loadData(E params);
    
    public abstract void clear();
    
    public abstract P getData();
    
    public abstract P processData(L data);
}