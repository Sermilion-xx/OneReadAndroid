package net.oneread.oneread.data.remote

import io.reactivex.Observable
import net.oneread.oneread.data.model.Article
import net.oneread.oneread.data.model.api.LoginResponse
import net.oneread.oneread.data.model.api.RegResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.CompletableFuture

interface OneReadService {

    @GET("spotlight/get") @FormUrlEncoded
    fun getSpotlight(): Observable<MutableList<Article>>

    @POST("spotlight/save") @FormUrlEncoded
    fun saveToBookmarks(items: Article): Observable<Article>

    @POST("/api/register") @FormUrlEncoded
    fun register(@Field("email") email: String,
                 @Field("password") password: String,
                 @Field("email") username: String,
                 @Field("name") name: String,
                 @Field("ilang") ilang: String): Observable<RegResponse>

    @POST("/api/login") @FormUrlEncoded
    fun login(@Field("email") email: String,
              @Field("password") password: String): Observable<LoginResponse>

    @POST("/api/login") @FormUrlEncoded
    fun loginSync(@Field("email") email: String,
              @Field("password") password: String): LoginResponse

    @POST("/api/login") @FormUrlEncoded
    fun loginAnonimous(@Field("grant_type") grant_type: String,
              @Field("device_id") device_id: String): Observable<LoginResponse>
}
