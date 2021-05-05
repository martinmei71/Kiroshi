package com.wappiter.app.infrastucture.di

import com.wappiter.app.GlobalApplication
import com.wappiter.app.infrastucture.di.modules.*
import com.wappiter.app.module.login.LoginComponent
import com.wappiter.app.module.login.LoginModule
import com.wappiter.app.module.main.MainComponent
import com.wappiter.app.module.main.MainModule
import com.wappiter.app.module.main.fragments.myrestaurants.MyRestaurantsFragmentComponent
import com.wappiter.app.module.main.fragments.myrestaurants.MyRestaurantsFragmentModule
import com.wappiter.app.module.main.fragments.scanner.ScannerFragmentComponent
import com.wappiter.app.module.main.fragments.scanner.ScannerFragmentModule
import com.wappiter.app.module.main.fragments.settings.SettingsFragmentComponent
import com.wappiter.app.module.main.fragments.settings.SettingsFragmentModule
import com.wappiter.app.module.myorders.MyOrdersComponent
import com.wappiter.app.module.myorders.MyOrdersModule
import com.wappiter.app.module.profile.EditProfileComponent
import com.wappiter.app.module.profile.EditProfileModule
import com.wappiter.app.module.registration.RegistrationComponent
import com.wappiter.app.module.registration.RegistrationModule
import com.wappiter.app.module.resetpassword.ResetPasswordComponent
import com.wappiter.app.module.resetpassword.ResetPasswordModule
import com.wappiter.app.module.restaurantdetail.RestaurantDetailComponent
import com.wappiter.app.module.restaurantdetail.RestaurantDetailModule
import com.wappiter.app.module.splash.SplashComponent
import com.wappiter.app.module.splash.SplashModule
import com.wappiter.app.module.termsandconditions.TermsAndConditionsComponent
import com.wappiter.app.module.termsandconditions.TermsAndConditionsModule
import com.wappiter.app.module.webview.generic.GenericWebviewComponent
import com.wappiter.app.module.webview.generic.GenericWebviewModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by porta on 18/05/17.
 */

@Singleton
@Component(modules = [AppModule::class, RetrofitModule::class, ApiModule::class, MappersModule::class, DomainModule::class, AppInfrastructureModule::class, UiModule::class, GlobalServicesModule::class])
interface AppComponent {

    fun inject(androidApp: GlobalApplication)

    operator fun plus(module: SplashModule): SplashComponent

    operator fun plus(module: MainModule): MainComponent

    operator fun plus(module: LoginModule): LoginComponent

    operator fun plus(module: RegistrationModule): RegistrationComponent

    operator fun plus(module: ResetPasswordModule): ResetPasswordComponent

    operator fun plus(module: ScannerFragmentModule): ScannerFragmentComponent

    operator fun plus(module: MyRestaurantsFragmentModule): MyRestaurantsFragmentComponent

    operator fun plus(module: SettingsFragmentModule): SettingsFragmentComponent

    operator fun plus(module: TermsAndConditionsModule): TermsAndConditionsComponent

    operator fun plus(module: GenericWebviewModule): GenericWebviewComponent

    operator fun plus(module: RestaurantDetailModule): RestaurantDetailComponent

    operator fun plus(module: EditProfileModule): EditProfileComponent

    operator fun plus(module: MyOrdersModule): MyOrdersComponent
}