package com.wappiter.app.data.constants.environment

/**
 * Created by Adrián Bao on 16/01/2018.
 */
object EnvironmentConstants {

    private var LOCALHOST = "http://192.168.1.39:3000"
    private const val API = "api/v1/"
    private var DEVELOPMENT = "https://wappiter-sandbox.softwhisper.es/"

    var DOMAIN_API = DEVELOPMENT + API
    var RESTAURANTS_URL = DEVELOPMENT + "restaurants/"
    var RESET_PASSWORD_URL = DEVELOPMENT + "password/new"
    var REGISTER_URL = DEVELOPMENT + "sign_up"
    var RESTAURANT_MANAGER_REGISTER_URL = DEVELOPMENT + "admin/sign_up"
    var FAVOURITES_RESTAURANTS_URL = DEVELOPMENT + "restaurants/favourites"
    var EDIT_PROFILE_URL = DEVELOPMENT + "users/edit_profile"
    var MY_ORDERS_URL = "/my_orders"
    var WEB_URL = "https://www.wappiter.com/"
    var TERMS_AND_CONDITIONS_URL = "https://www.wappiter.com/politica-de-privacidad/"

}