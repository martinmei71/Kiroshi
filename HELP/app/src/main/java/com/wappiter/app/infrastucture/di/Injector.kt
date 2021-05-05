package com.wappiter.app.infrastucture.di

import com.wappiter.app.GlobalApplication
import com.wappiter.app.infrastucture.di.modules.AppModule

/**
 * Created by porta on 18/05/17.
 */

object Injector {

    private var appComponent: AppComponent? = null

    fun init(application: GlobalApplication) {

        appComponent = DaggerAppComponent.builder().appModule(AppModule(application)).build()
    }

    fun appComponent(): AppComponent? {

        return appComponent
    }
}
