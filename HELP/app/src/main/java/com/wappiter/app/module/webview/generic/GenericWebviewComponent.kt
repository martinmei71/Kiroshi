package com.wappiter.app.module.webview.generic

import com.wappiter.app.infrastucture.di.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [GenericWebviewModule::class])

interface GenericWebviewComponent {

    fun inject(genericWebviewActivity: GenericWebviewActivity)
}