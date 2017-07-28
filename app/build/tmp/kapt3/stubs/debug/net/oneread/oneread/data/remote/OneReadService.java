package net.oneread.oneread.data.remote;

@kotlin.Metadata(mv = {1, 1, 6}, bv = {1, 0, 1}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H\'J\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u00032\b\b\u0001\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\tH\'J@\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00032\b\b\u0001\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\t2\b\b\u0001\u0010\r\u001a\u00020\t2\b\b\u0001\u0010\u000e\u001a\u00020\t2\b\b\u0001\u0010\u000f\u001a\u00020\tH\'J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u00032\u0006\u0010\u0011\u001a\u00020\u0005H\'\u00a8\u0006\u0012"}, d2 = {"Lnet/oneread/oneread/data/remote/OneReadService;", "", "getSpotlight", "Lio/reactivex/Observable;", "", "Lnet/oneread/oneread/data/model/Article;", "login", "Lnet/oneread/oneread/data/model/LoginResponse;", "email", "", "password", "register", "Lnet/oneread/oneread/data/model/RegResponse;", "username", "name", "ilang", "saveToBookmarks", "items", "app_debug"})
public abstract interface OneReadService {
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.FormUrlEncoded()
    @retrofit2.http.GET(value = "spotlight/get")
    public abstract io.reactivex.Observable<java.util.List<net.oneread.oneread.data.model.Article>> getSpotlight();
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.FormUrlEncoded()
    @retrofit2.http.POST(value = "spotlight/save")
    public abstract io.reactivex.Observable<net.oneread.oneread.data.model.Article> saveToBookmarks(@org.jetbrains.annotations.NotNull()
    net.oneread.oneread.data.model.Article items);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.FormUrlEncoded()
    @retrofit2.http.POST(value = "/api/register")
    public abstract io.reactivex.Observable<net.oneread.oneread.data.model.RegResponse> register(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "email")
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "password")
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "username")
    java.lang.String username, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "name")
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "ilang")
    java.lang.String ilang);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.FormUrlEncoded()
    @retrofit2.http.POST(value = "/api/login")
    public abstract io.reactivex.Observable<net.oneread.oneread.data.model.LoginResponse> login(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "email")
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "password")
    java.lang.String password);
}