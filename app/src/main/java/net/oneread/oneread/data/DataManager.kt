package net.oneread.oneread.data


import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import net.oneread.oneread.data.model.Article
import net.oneread.oneread.data.model.RegResponse
import net.oneread.oneread.data.remote.OneReadService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject constructor(private val oneReadService: OneReadService) {

    fun getSpotlight(): Observable<MutableList<Article>> {
        return oneReadService.getSpotlight()
    }

    fun saveToBookmarks(items: Article): Observable<Article> {
        return oneReadService.saveToBookmarks(items)
    }

    fun register(email: String,
                 password: String,
                 username: String,
                 name: String,
                 ilang: String): Observable<RegResponse> {
        return oneReadService.register(email, password, username, name, ilang)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
