package net.oneread.oneread.ui.base

/**
 * Every presenter in the app must either implement this interface or extend BaseMvpPresenter
 * indicating the MvpView type that wants to be attached with.
 */
interface MvpPresenter<in V : MvpView> {
    fun attachView(view: V)
    fun detachView()
}
