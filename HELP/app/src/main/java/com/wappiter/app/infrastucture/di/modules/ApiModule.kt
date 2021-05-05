package com.wappiter.app.infrastucture.di.modules

import com.wappiter.data.ApiRetrofit
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by porta on 27/11/17.
 */
@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideApiRetrofit(retrofit: Retrofit): ApiRetrofit {
        return retrofit.create(ApiRetrofit::class.java)
    }
}