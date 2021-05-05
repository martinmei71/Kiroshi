package com.wappiter.app.presentation.base

import com.wappiter.app.base.BaseRouter
import com.wappiter.app.presentation.base.BaseVP.Presenter
import com.wappiter.app.presentation.base.invoker.InteractorExecution
import com.wappiter.app.presentation.base.invoker.InteractorInvoker
import com.wappiter.app.util.Utils
import com.wappiter.domain.base.interactor.baseerrors.ApiInteractorError
import com.wappiter.domain.base.interactor.baseerrors.BaseInteractorError
import com.wappiter.domain.base.interactor.baseerrors.InteractorError
import com.wappiter.domain.base.interactorerror.UnauthorizedInteractorError
import com.wappiter.domain.base.interactorerror.UserNotLoggedInteractorError
import com.wappiter.domain.firebase.FirebaseAnalyticsDatasource
import com.wappiter.domain.user.logout.interactor.LogoutInteractor
import com.wappiter.infrastructure.accesor.ResourcesAccessor

abstract class BasePresenter<V : BaseVP.View?>(protected var mInteractorInvoker: InteractorInvoker,
                                               protected var mFirebaseAnalyticsDatasource: FirebaseAnalyticsDatasource,
                                               private val mCustomViewInjector: CustomViewInjector,
                                               private val mLogoutInteractor: LogoutInteractor,
                                               private val mBaseRouter: BaseRouter,
                                               protected var mResourcesAccessor: ResourcesAccessor) : Presenter {

    var view: V? = null
        private set

    @Suppress("UNCHECKED_CAST")
    override fun attachView(v: Any) {
        val viewAsBaseView = v as V
        view = mCustomViewInjector.injectView(viewAsBaseView)
        onViewAttached()
    }

    private fun onViewAttached() {
        view?.setUpView()
    }

    override fun onStart(screenName: String) {
        mFirebaseAnalyticsDatasource.setCurrentScreen(screenName)
    }

    override fun detachView() {
        view = mCustomViewInjector.nullObjectPatternView(view)
    }

    override fun logout() {
        InteractorExecution(mLogoutInteractor).result { mBaseRouter.goToLogin() }.error(
                UserNotLoggedInteractorError::class.java,
                InteractorResult { _: UserNotLoggedInteractorError? -> mBaseRouter.goToLogin() }).error(
                BaseInteractorError::class.java,
                InteractorResult { _: BaseInteractorError? -> mBaseRouter.goToLogin() }).execute(mInteractorInvoker)
    }

    protected open fun handleError(error: InteractorError,
                                   showMessage: Boolean = true) {
        view?.logError("Error type: ".plus(error.type), error.toString())

        if (error is UnauthorizedInteractorError || error is UserNotLoggedInteractorError) {
            logout()
        } else if (showMessage) {
            when {
                error is ApiInteractorError && Utils.isNotEmpty(error.apiError.message) -> {
                    view?.showError(error.apiError.message)
                }
                else -> {
                    view!!.showError()
                }
            }
        }
    }
}