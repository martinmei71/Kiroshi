package com.wappiter.app.base

import android.app.AlertDialog
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.afollestad.materialdialogs.MaterialDialog
import com.wappiter.app.R
import com.wappiter.app.infrastucture.di.AppComponent
import com.wappiter.app.infrastucture.di.Injector
import com.wappiter.app.presentation.base.BaseVP


/**
 * Created by porta on 18/05/17.
 */

abstract class BaseActivity : AppCompatActivity(), BaseVP.View {

    private var mProgressDialog: AlertDialog? = null

    protected abstract fun getActivityTag(): String

    //Usado para enviar a analytics (Es constante y no se traduce)
    protected abstract fun getAnalyticsName(): String

    protected abstract fun getPresenter(): BaseVP.Presenter

    protected abstract fun makeInjection(appComponent: AppComponent?)

    protected abstract fun detachView()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        makeInjection(Injector.appComponent())
    }

    override fun onStart() {
        super.onStart()
        getPresenter().onStart(getAnalyticsName())
    }

    override fun onDestroy() {
        detachView()
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun logInfo(message: String) {
        Log.i(getActivityTag(), message)
    }

    override fun logDebug(message: String) {
        Log.d(getActivityTag(), message)
    }

    override fun logError(error: String,
                          message: String) {
        Log.e(getActivityTag(), "[$error] $message")
    }

    override fun showError() {
        if (!isFinishing && !isDestroyed) {
            MaterialDialog(this)
                    .title(R.string.app_name)
                    .message(R.string.error_generic)
                    .positiveButton(android.R.string.ok)
                    .show()
        }
    }

    override fun showError(message: String) {
        if (!isFinishing && !isDestroyed) {
            MaterialDialog(this)
                    .title(R.string.app_name)
                    .message(text = message)
                    .positiveButton(android.R.string.ok)
                    .show()
        }
    }

    override fun showError(title: String, message: String) {
        if (!isFinishing && !isDestroyed) {
            MaterialDialog(this)
                    .title(text = title)
                    .message(text = message)
                    .positiveButton(android.R.string.ok)
                    .show()
        }
    }

    override fun showToastMessage(message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showProgressDialog(message: String) {
        if (mProgressDialog == null) {
            mProgressDialog = initProgressDialog(message)
        }
        mProgressDialog!!.show()
    }

    override fun showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = initProgressDialog(getString(R.string.action_loading_popup))
        }
        mProgressDialog!!.show()
    }

    override fun hideProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog!!.dismiss()
            mProgressDialog = null
        }
    }

    private fun initProgressDialog(message: String): AlertDialog {
        val llPadding = 30
        val llPaddingLeftRight = 50
        val ll = LinearLayout(this)
        ll.orientation = LinearLayout.HORIZONTAL
        ll.setPadding(llPaddingLeftRight, llPadding, llPaddingLeftRight, llPadding)
        ll.gravity = Gravity.CENTER
        var llParam = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
        llParam.gravity = Gravity.CENTER

        ll.layoutParams = llParam

        val progressBar = ProgressBar(this)
        progressBar.isIndeterminate = true
        progressBar.setPadding(0, 0, llPadding, 0)
        progressBar.layoutParams = llParam
        progressBar.indeterminateDrawable.setColorFilter(ContextCompat.getColor(this, android.R.color.black), PorterDuff.Mode.SRC_IN)
        progressBar.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white))

        llParam = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        llParam.gravity = Gravity.CENTER
        val tvText = TextView(this)
        tvText.text = message
        tvText.setTextColor(ContextCompat.getColor(this, android.R.color.black))
        tvText.textSize = 16.toFloat()
        tvText.layoutParams = llParam
        tvText.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white))

        ll.addView(progressBar)
        ll.addView(tvText)
        ll.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white))

        val builder = AlertDialog.Builder(this, R.style.CustomDialog)
        builder.setCancelable(false)
        builder.setView(ll)

        val dialog = builder.create()
        val window = dialog.window
        if (window != null) {
            val layoutParams = WindowManager.LayoutParams()
            layoutParams.copyFrom(dialog.window?.attributes)
            layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
            dialog.window?.attributes = layoutParams
        }
        return dialog
    }
}