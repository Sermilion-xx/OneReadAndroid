package net.oneread.oneread.data


import net.oneread.oneread.data.model.Article
import net.oneread.oneread.data.remote.OneReadService
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class DataManager @Inject constructor(private val oneReadService: OneReadService) {

    fun getSpotlight(): Observable<MutableList<Article>> {
        return oneReadService.getSpotlight()
    }

    fun saveToBookmarks(items: Article): Observable<Article> {
        return oneReadService.saveToBookmarks(items)
    }
}
