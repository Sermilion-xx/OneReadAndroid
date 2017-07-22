package net.oneread.oneread.injection.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import net.oneread.oneread.data.remote.OneReadService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
//        httpClient.addInterceptor { chain ->
//            val request = chain.request()
//                    .newBuilder()
//                    .addHeader("Authorization", " Basic " + authString).build()
//            chain.proceed(request)
//        }
        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideOneAccountService(okHttpClient: OkHttpClient, gson: Gson): OneReadService {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://52.164.247.48:8080/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(OneReadService::class.java)
    }
}
