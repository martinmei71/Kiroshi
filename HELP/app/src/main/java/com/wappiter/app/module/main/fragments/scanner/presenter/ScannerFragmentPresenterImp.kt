package com.wappiter.app.module.main.fragments.scanner.presenter

import android.net.Uri
import android.util.Log
import com.google.zxing.BarcodeFormat
import com.google.zxing.Result
import com.wappiter.app.BuildConfig
import com.wappiter.app.constants.AppConstants
import com.wappiter.app.module.main.fragments.scanner.router.ScannerFragmentRouter
import com.wappiter.app.presentation.base.BasePresenter
import com.wappiter.app.presentation.base.CustomViewInjector
import com.wappiter.app.presentation.base.invoker.InteractorExecution
import com.wappiter.app.presentation.base.invoker.InteractorInvoker
import com.wappiter.app.util.bus.OnAddFavouriteRestaurantEvent
import com.wappiter.domain.base.interactor.baseerrors.BaseInteractorError
import com.wappiter.domain.base.interactor.baseerrors.InteractorError
import com.wappiter.domain.firebase.FirebaseAnalyticsDatasource
import com.wappiter.domain.restaurant.addfavourite.interactor.AddFavouriteInteractor
import com.wappiter.domain.restaurant.addfavourite.interactor.AddFavouritesInteractorValues
import com.wappiter.domain.user.UserSession
import com.wappiter.domain.user.logout.interactor.LogoutInteractor
import com.wappiter.domain.user.profile.interactor.GetUserLoggedInteractor
import com.wappiter.domain.user.profile.interactor.UserProfileInteractor
import com.wappiter.infrastructure.accesor.ResourcesAccessor
import org.greenrobot.eventbus.EventBus

class ScannerFragmentPresenterImp(interactorInvoker: InteractorInvoker,
                                  val mGetUserLoggedInteractor: GetUserLoggedInteractor,
                                  val mUserProfileInteractor: UserProfileInteractor,
                                  val mAddFavouriteInteractor: AddFavouriteInteractor,
                                  logoutInteractor: LogoutInteractor,
                                  resourcesAccessor: ResourcesAccessor,
                                  firebaseAnalyticsDatasource: FirebaseAnalyticsDatasource,
                                  val mRouter: ScannerFragmentRouter,
                                  customViewInjector: CustomViewInjector) :
        BasePresenter<ScannerFragmentView>(interactorInvoker, firebaseAnalyticsDatasource, customViewInjector, logoutInteractor, mRouter, resourcesAccessor), ScannerFragmentPresenter {

    override fun didOnResume() {
        view?.checkCameraPermission()
    }

    override fun didOnPause() {
        view?.stopScanner()
    }

    override fun didOnPermissionChecked() {
        view?.setScannerView()
    }

    override fun didOnPermissionDenied() {
        view?.showCameraPermissionInfoView()
    }

    override fun didReadCode(result: Result) {
        view?.resumeCameraPreview()
        view?.playSound()
        view?.stopScanner()
        checkQRResult(result)
    }

    override fun didCloseQRErrorDialog() {
        view?.checkCameraPermission()
    }

    override fun didClickGoToSettings() {
        view?.openAppPermissionSettings()
    }

    override fun didClickEnableCameraPermission() {
        view?.openAppPermissionSettings()
    }

    private fun getUserLogged(restaurantId: String) {
        InteractorExecution(mGetUserLoggedInteractor).result { this.onGetUserLoggedSuccess(it, restaurantId) }.error(BaseInteractorError::class.java) { this.onGetUserLoggedFailure(it, restaurantId) }.execute(mInteractorInvoker)
    }

    private fun onGetUserLoggedSuccess(userSession: UserSession, restaurantId: String) {
        addFavourite(restaurantId)
    }

    private fun onGetUserLoggedFailure(error: InteractorError, restaurantId: String) {
        view?.hideProgressDialog()
        mRouter.goToRestaurantDetail(restaurantId)
    }

    private fun checkQRResult(qrResult: Result) {
        view?.showProgressDialog()
        val resultText = qrResult.text
        val resultFormatText = qrResult.barcodeFormat.toString()
        if (BuildConfig.DEBUG) {
            Log.d("ScannerFragment", "checkQRResult -> $resultText")
            Log.d("ScannerFragment", "resultFormatText -> $resultFormatText")
        }

        val uri = Uri.parse(resultText)
        var restaurantId: String? = null
        try {
            restaurantId = uri.getQueryParameter(AppConstants.SCANNER_RESTAURANT_ID_PARAM)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (BarcodeFormat.QR_CODE.toString() == resultFormatText && restaurantId != null && restaurantId.isNotEmpty()) {
            getUserLogged(restaurantId)
        } else {
            view?.hideProgressDialog()
            view?.showQRError()
        }
    }

    private fun addFavourite(restaurantId: String) {
        mAddFavouriteInteractor.setInteractorValues(AddFavouritesInteractorValues(restaurantId))
        InteractorExecution(mAddFavouriteInteractor).result { onAddFavouriteSuccess(restaurantId) }.error(BaseInteractorError::class.java) { this.onAddFavouriteFailure(it, restaurantId) }.execute(mInteractorInvoker)
    }

    private fun onAddFavouriteSuccess(restaurantId: String) {
        getUpdatedUserProfile(restaurantId)
    }

    private fun onAddFavouriteFailure(error: InteractorError, restaurantId: String) {
        view?.hideProgressDialog()
        mRouter.goToRestaurantDetail(restaurantId)
    }

    private fun getUpdatedUserProfile(restaurantId: String) {
        InteractorExecution(mUserProfileInteractor).result { this.onGetUpdatedUserProfileSuccess(it, restaurantId) }.error(BaseInteractorError::class.java) { this.onGetUpdatedUserProfileFailure(it, restaurantId) }.execute(mInteractorInvoker)
    }

    private fun onGetUpdatedUserProfileSuccess(userSession: UserSession, restaurantId: String) {
        view?.hideProgressDialog()
        EventBus.getDefault().post(OnAddFavouriteRestaurantEvent())
        mRouter.goToRestaurantDetail(restaurantId)
    }

    private fun onGetUpdatedUserProfileFailure(error: InteractorError, restaurantId: String) {
        view?.hideProgressDialog()
        mRouter.goToRestaurantDetail(restaurantId)
    }
}