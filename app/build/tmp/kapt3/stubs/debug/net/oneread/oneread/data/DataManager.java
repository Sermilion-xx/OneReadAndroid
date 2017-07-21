package net.oneread.oneread.data;

@kotlin.Metadata(mv = {1, 1, 6}, bv = {1, 0, 1}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0017\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006J\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010\n\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lnet/oneread/oneread/data/DataManager;", "", "oneReadService", "Lnet/oneread/oneread/data/remote/OneReadService;", "(Lnet/oneread/oneread/data/remote/OneReadService;)V", "getSpotlight", "Lrx/Observable;", "", "Lnet/oneread/oneread/data/model/Article;", "saveToBookmarks", "items", "app_debug"})
@javax.inject.Singleton()
public class DataManager {
    private final net.oneread.oneread.data.remote.OneReadService oneReadService = null;
    
    @org.jetbrains.annotations.NotNull()
    public final rx.Observable<java.util.List<net.oneread.oneread.data.model.Article>> getSpotlight() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final rx.Observable<net.oneread.oneread.data.model.Article> saveToBookmarks(@org.jetbrains.annotations.NotNull()
    net.oneread.oneread.data.model.Article items) {
        return null;
    }
    
    @javax.inject.Inject()
    public DataManager(@org.jetbrains.annotations.NotNull()
    net.oneread.oneread.data.remote.OneReadService oneReadService) {
        super();
    }
}