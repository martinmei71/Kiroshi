package com.wappiter.data

import com.wappiter.data.appsession.api.param.AppSessionParam
import com.wappiter.data.appsession.api.result.AppSessionApiModel
import com.wappiter.data.base.mapper.ApiVoid
import com.wappiter.data.user.UserApiModel
import com.wappiter.data.user.device.api.DeviceApiModel
import com.wappiter.data.user.device.api.DeviceRequestParams
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by porta on 27/11/17.
 */
interface ApiRetrofit {

    @POST("sessions/create")
    fun createSession(@Body appSessionParam: AppSessionParam): Call<AppSessionApiModel>

    @FormUrlEncoded
    @POST("users/sign_up")
    fun register(@Field("first_name") firstName: String,
                 @Field("last_name") lastName: String,
                 @Field("email") email: String,
                 @Field("photoUrl") photoUrl: String?,
                 @Field("id") userId: String?,
                 @Field("provider_id") tokenId: String?,
                 @Field("provider_name") providerName: String,
                 @Field("password") password: String?): Call<UserApiModel>

    @FormUrlEncoded
    @POST("users/login")
    fun loginWithEmail(@Field("email") email: String,
                       @Field("password") password: String): Call<UserApiModel>

    @FormUrlEncoded
    @POST("users/login_with_api_key")
    fun loginWithApiKey(@Field("Api-Key") apiKey: String): Call<UserApiModel>

    @FormUrlEncoded
    @POST("users/login_with_identity")
    fun loginWithIdentity(@Field("first_name") firstName: String,
                          @Field("last_name") lastName: String,
                          @Field("email") email: String,
                          @Field("photoUrl") photoUrl: String?,
                          @Field("id") userId: String,
                          @Field("provider_id") tokenId: String,
                          @Field("provider_name") providerName: String): Call<UserApiModel>

    @FormUrlEncoded
    @PATCH("users/confirm")
    fun confirm(@Field("email") email: String,
                @Field("code") code: String): Call<UserApiModel>

    @FormUrlEncoded
    @PATCH("users/request_confirmation_code")
    fun requestConfirmationCode(@Field("email") email: String): Call<ApiVoid>

    @FormUrlEncoded
    @POST("users/request_reset_password_code")
    fun requestResetPasswordCode(@Field("email") email: String): Call<ApiVoid>

    @FormUrlEncoded
    @POST("users/reset_password")
    fun resetPassword(@Field("email") email: String,
                      @Field("code") code: String,
                      @Field("password") newPassword: String): Call<ApiVoid>

    @POST("users/logout")
    fun logout(): Call<ApiVoid>

    @GET("users/profile")
    fun getUserProfile(@Query("Api-Key") apiKey: String): Call<UserApiModel>

    @FormUrlEncoded
    @PATCH("devices/update")
    fun updateDevice(@Body device: DeviceRequestParams): Call<DeviceApiModel>

    @POST("restaurants/{id}/favourite")
    fun addFavourite(@Path("id") restaurantId: String): Call<ApiVoid>
}