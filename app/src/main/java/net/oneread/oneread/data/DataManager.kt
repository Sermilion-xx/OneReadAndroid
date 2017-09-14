package net.oneread.oneread.data


import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import net.oneread.oneread.data.model.Article
import net.oneread.oneread.data.model.api.LoginResponse
import net.oneread.oneread.data.model.api.RegResponse
import net.oneread.oneread.data.remote.OneReadService
import java.util.concurrent.CompletableFuture
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject constructor(private val oneReadService: OneReadService) {

    fun getSpotlight(): Observable<MutableList<Article>> = oneReadService.getSpotlight()

    fun saveToBookmarks(items: Article): Observable<Article> = oneReadService.saveToBookmarks(items)

    fun register(email: String,
                 password: String,
                 username: String,
                 name: String,
                 ilang: String): Observable<RegResponse> {
        return oneReadService.register(email, password, username, name, ilang)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun login(email: String, password: String): Observable<LoginResponse> {
        return oneReadService.login(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loginSync(email: String, password: String): LoginResponse =
            oneReadService.loginSync(email, password)

    fun loginAnonimous(grant_type: String, device_id: String): Observable<LoginResponse> {
        return oneReadService.loginAnonimous(grant_type, device_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
