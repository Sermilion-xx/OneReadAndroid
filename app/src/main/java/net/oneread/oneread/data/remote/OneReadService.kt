package net.oneread.oneread.data.remote

import net.oneread.oneread.data.model.Article
import retrofit2.http.GET
import retrofit2.http.POST
import rx.Observable

interface OneReadService {
    @GET("spotlight/get")
    fun getSpotlight(): Observable<MutableList<Article>>

    @POST("spotlight/save")
    fun saveToBookmarks(items: Article): Observable<Article>
}
