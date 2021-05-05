package com.wappiter.data.base.error

import com.wappiter.domain.base.globalexception.ApiError
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object ApiErrorParser {
    fun parseError(response: Response<*>): ApiError? {
        //TODO mejorar esto pero por lo de ahora nos vale para hacer el parseo
        val converter: Converter<ResponseBody?, ApiError> = Retrofit.Builder()
                .baseUrl("http://google.es")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .responseBodyConverter<ApiError>(ApiError::class.java, arrayOfNulls(0))
        val error: ApiError?
        try {
            error = converter.convert(response.errorBody())
        } catch (e: IOException) {
            return null
        }
        return error
    }
}