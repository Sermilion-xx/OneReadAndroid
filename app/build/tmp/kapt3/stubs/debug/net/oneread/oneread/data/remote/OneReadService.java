package net.oneread.oneread.data.remote;

@kotlin.Metadata(mv = {1, 1, 6}, bv = {1, 0, 1}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00032\u0006\u0010\u0007\u001a\u00020\u0005H\'\u00a8\u0006\b"}, d2 = {"Lnet/oneread/oneread/data/remote/OneReadService;", "", "getSpotlight", "Lrx/Observable;", "", "Lnet/oneread/oneread/data/model/Article;", "saveToBookmarks", "items", "app_debug"})
public abstract interface OneReadService {
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "spotlight/get")
    public abstract rx.Observable<java.util.List<net.oneread.oneread.data.model.Article>> getSpotlight();
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "spotlight/save")
    public abstract rx.Observable<net.oneread.oneread.data.model.Article> saveToBookmarks(@org.jetbrains.annotations.NotNull()
    net.oneread.oneread.data.model.Article items);
}