package com.wappiter.app.module.main.fragments.scanner

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.media.AudioManager
import android.media.ToneGenerator
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.afollestad.materialdialogs.MaterialDialog
import com.google.zxing.BarcodeFormat
import com.google.zxing.Result
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.wappiter.app.R
import com.wappiter.app.base.BaseFragment
import com.wappiter.app.infrastucture.di.AppComponent
import com.wappiter.app.module.main.fragments.scanner.presenter.ScannerFragmentPresenter
import com.wappiter.app.module.main.fragments.scanner.presenter.ScannerFragmentView
import com.wappiter.app.presentation.base.BaseVP
import com.wappiter.app.util.analytics.ScreenNames
import kotlinx.android.synthetic.main.fragment_scanner.*
import me.dm7.barcodescanner.zxing.ZXingScannerView
import javax.inject.Inject

class ScannerFragment : BaseFragment(), ScannerFragmentView, ZXingScannerView.ResultHandler {

    val TAG: String = ScannerFragment::class.toString()

    private var mScannerView: ZXingScannerView? = null
    private var mAlreadyAskedAboutCameraPermissions = false

    @Inject
    lateinit var mPresenter: ScannerFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_scanner, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        mPresenter.attachView(this)
    }

    override fun onResume() {
        super.onResume()
        mPresenter.didOnResume()
    }

    override fun onPause() {
        super.onPause()
        mPresenter.didOnPause()
    }

    override fun detachView() {
        mPresenter.detachView()
    }

    override fun getFragmentTag(): String {
        return TAG
    }

    override fun getAnalyticsName(): String {
        return ScreenNames.SCANNER
    }

    override fun getPresenter(): BaseVP.Presenter {
        return mPresenter
    }

    override fun makeInjection(appComponent: AppComponent?) {
        appComponent?.plus(ScannerFragmentModule(requireActivity()))?.inject(this)
    }

    override fun setUpView() {
        initializeScanner()

        enable_camera_permissions_btn.setOnClickListener {
            mPresenter.didClickEnableCameraPermission()
        }
    }

    private fun initializeScanner() {
        mScannerView = ZXingScannerView(activity)
        scanner_fragment_content_frame!!.addView(mScannerView)
    }

    override fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this.requireActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_DENIED) {
            mPresenter.didOnPermissionChecked()

        } else if (!mAlreadyAskedAboutCameraPermissions) {
            mAlreadyAskedAboutCameraPermissions = true
            Dexter
                    .withActivity(this.requireActivity())
                    .withPermissions(Manifest.permission.CAMERA)
                    .withListener(object : MultiplePermissionsListener {
                        override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                            if (report.areAllPermissionsGranted()) {
                                mPresenter.didOnPermissionChecked()
                            } else {
                                mPresenter.didOnPermissionDenied()
                            }
                        }

                        override fun onPermissionRationaleShouldBeShown(permissions: List<PermissionRequest>, token: PermissionToken) {
                            token.continuePermissionRequest()
                            mPresenter.didOnPermissionDenied()
                        }
                    })
                    .check()
        }
    }

    override fun setScannerView() {
        mScannerView?.setResultHandler(this)
        mScannerView?.startCamera()
        mScannerView?.setAutoFocus(true)
        mScannerView?.setFormats(getValidFormats())
    }

    override fun resumeCameraPreview() {
        mScannerView?.resumeCameraPreview(this)
    }

    override fun showCameraPermissionInfoView() {
        content_view_camera_permisions_info.visibility = View.VISIBLE
    }

    override fun stopScanner() {
        mScannerView?.stopCamera()
    }

    override fun playSound() {
        val toneGen = ToneGenerator(AudioManager.STREAM_MUSIC, 100)
        toneGen.startTone(ToneGenerator.TONE_SUP_PIP, 150)
    }

    override fun openAppPermissionSettings() {
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        val uri = Uri.fromParts("package", requireActivity().packageName, null)
        intent.data = uri
        startActivity(intent)
    }

    override fun showQRError() {
        val parentActivity: Activity? = activity
        if (parentActivity != null && !parentActivity.isDestroyed) {
            MaterialDialog(parentActivity).show {
                title(R.string.scanner_invalid_qr_title)
                message(R.string.scanner_invalid_qr_message)
                positiveButton(R.string.accept) { mPresenter.didCloseQRErrorDialog() }
                cancelOnTouchOutside(false)
            }
        }
    }

    private fun getValidFormats(): List<BarcodeFormat>? {
        val validFormats: List<BarcodeFormat> = ArrayList()
        validFormats.toMutableList().add(BarcodeFormat.QR_CODE)
        return validFormats
    }

    /**
     * ZXingScannerView.ResultHandler
     * @param result QRCODE
     */
    override fun handleResult(result: Result) {
        mPresenter.didReadCode(result)
    }
}