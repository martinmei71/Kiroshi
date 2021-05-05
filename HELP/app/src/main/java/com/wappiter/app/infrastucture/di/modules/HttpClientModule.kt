package com.wappiter.app.infrastucture.di.modules

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by porta on 18/05/17.
 */
@Module
class HttpClientModule {
    @Provides
    @Singleton
    fun provideRetrofitBuilder(gson: Gson?,
                               okHttpClient: OkHttpClient?): Retrofit.Builder {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson)).client(okHttpClient)
    }
}