package com.wappiter.app

import android.app.Application
import com.google.android.gms.analytics.Tracker
import com.wappiter.app.infrastucture.di.Injector
import com.wappiter.domain.user.User
import io.branch.referral.Branch
import org.parceler.ParcelClass
import org.parceler.ParcelClasses

@ParcelClasses(ParcelClass(User::class))
class GlobalApplication : Application() {

    private var mAppTracker: Tracker? = null

    override fun onCreate() {
        super.onCreate()
        initInjector()
        initBranchIO()
    }

    private fun initInjector() {
        Injector.init(this)
    }

    private fun initBranchIO() {
        Branch.enableDebugMode()
        Branch.getAutoInstance(this)
    }
}