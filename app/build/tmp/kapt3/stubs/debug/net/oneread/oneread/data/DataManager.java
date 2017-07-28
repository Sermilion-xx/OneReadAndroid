package net.oneread.oneread.data;

@kotlin.Metadata(mv = {1, 1, 6}, bv = {1, 0, 1}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006J\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fJ4\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\fJ\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010\u0014\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lnet/oneread/oneread/data/DataManager;", "", "oneReadService", "Lnet/oneread/oneread/data/remote/OneReadService;", "(Lnet/oneread/oneread/data/remote/OneReadService;)V", "getSpotlight", "Lio/reactivex/Observable;", "", "Lnet/oneread/oneread/data/model/Article;", "login", "Lnet/oneread/oneread/data/model/LoginResponse;", "email", "", "password", "register", "Lnet/oneread/oneread/data/model/RegResponse;", "username", "name", "ilang", "saveToBookmarks", "items", "app_debug"})
@javax.inject.Singleton()
public final class DataManager {
    private final net.oneread.oneread.data.remote.OneReadService oneReadService = null;
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<java.util.List<net.oneread.oneread.data.model.Article>> getSpotlight() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<net.oneread.oneread.data.model.Article> saveToBookmarks(@org.jetbrains.annotations.NotNull()
    net.oneread.oneread.data.model.Article items) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<net.oneread.oneread.data.model.RegResponse> register(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String username, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String ilang) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Observable<net.oneread.oneread.data.model.LoginResponse> login(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password) {
        return null;
    }
    
    @javax.inject.Inject()
    public DataManager(@org.jetbrains.annotations.NotNull()
    net.oneread.oneread.data.remote.OneReadService oneReadService) {
        super();
    }
}