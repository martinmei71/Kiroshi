package com.wappiter.app.module.termsandconditions.presenter

import com.wappiter.app.data.constants.environment.EnvironmentConstants
import com.wappiter.app.module.termsandconditions.router.TermsAndConditionsRouter
import com.wappiter.app.presentation.base.BasePresenter
import com.wappiter.app.presentation.base.CustomViewInjector
import com.wappiter.app.presentation.base.invoker.InteractorExecution
import com.wappiter.app.presentation.base.invoker.InteractorInvoker
import com.wappiter.domain.base.interactor.baseerrors.BaseInteractorError
import com.wappiter.domain.base.interactor.baseerrors.InteractorError
import com.wappiter.domain.firebase.FirebaseAnalyticsDatasource
import com.wappiter.domain.user.logout.interactor.LogoutInteractor
import com.wappiter.domain.user.termsandconditions.interactor.GetAcceptedTermsAndConditionsInteractor
import com.wappiter.domain.user.termsandconditions.interactor.SetAcceptedTermsAndConditionsInteractor
import com.wappiter.domain.user.termsandconditions.interactor.SetAcceptedTermsAndConditionsInteractorValues
import com.wappiter.infrastructure.accesor.ResourcesAccessor

class TermsAndConditionsPresenterImp(interactorInvoker: InteractorInvoker,
                                     val mGetAcceptedTermsAndConditionsInteractor: GetAcceptedTermsAndConditionsInteractor,
                                     val mSetAcceptedTermsAndConditionsInteractor: SetAcceptedTermsAndConditionsInteractor,
                                     logoutInteractor: LogoutInteractor,
                                     resourcesAccessor: ResourcesAccessor,
                                     firebaseAnalyticsDatasource: FirebaseAnalyticsDatasource,
                                     val mRouter: TermsAndConditionsRouter,
                                     customViewInjector: CustomViewInjector) :
        BasePresenter<TermsAndConditionsView>(interactorInvoker, firebaseAnalyticsDatasource, customViewInjector, logoutInteractor, mRouter, resourcesAccessor), TermsAndConditionsPresenter {

    private var mRestaurantId = ""

    override fun startFlow() {
        getAcceptedTermsAndConditions()
    }

    override fun startFlowWithRestaurantId(restaurantId: String) {
        mRestaurantId = restaurantId
        getAcceptedTermsAndConditions()
    }

    override fun didClickTermsAndConditions() {
        mRouter.goToGenericWebview(EnvironmentConstants.TERMS_AND_CONDITIONS_URL)
    }

    override fun didAcceptedTermsAndConditions() {
        setAcceptedTermsAndConditions()
    }

    private fun getAcceptedTermsAndConditions() {
        InteractorExecution(mGetAcceptedTermsAndConditionsInteractor).result { this.onGetAcceptedTermsAndConditionsSuccess(it) }.error(BaseInteractorError::class.java) { this.onGetAcceptedTermsAndConditionsFailure(it) }.execute(mInteractorInvoker)
    }

    private fun onGetAcceptedTermsAndConditionsSuccess(accepted: Boolean) {
        if (accepted) {
            goToNextActivity()
        } else {
            view?.showTermsAndConditionsView()
        }
    }

    private fun onGetAcceptedTermsAndConditionsFailure(error: InteractorError) {
        view?.showTermsAndConditionsView()
    }

    private fun setAcceptedTermsAndConditions() {
        mSetAcceptedTermsAndConditionsInteractor.setInteractorValues(SetAcceptedTermsAndConditionsInteractorValues(true))
        InteractorExecution(mSetAcceptedTermsAndConditionsInteractor).result { this.onSetAcceptedTermsAndConditionsSuccess() }.error(BaseInteractorError::class.java) { this.onSetAcceptedTermsAndConditionsFailure(it) }.execute(mInteractorInvoker)
    }

    private fun onSetAcceptedTermsAndConditionsSuccess() {
        goToNextActivity()
    }

    private fun onSetAcceptedTermsAndConditionsFailure(error: InteractorError) {
        goToNextActivity()
    }

    private fun goToNextActivity() {
        if (mRestaurantId.isEmpty()) {
            mRouter.goToMain()
        } else {
            mRouter.goToMainFromDeeplink(mRestaurantId)
        }
    }
}