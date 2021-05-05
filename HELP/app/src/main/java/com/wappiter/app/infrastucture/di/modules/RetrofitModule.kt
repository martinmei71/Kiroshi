package com.wappiter.app.infrastucture.di.modules

import com.wappiter.app.BuildConfig
import com.wappiter.app.GlobalApplication
import com.wappiter.app.data.constants.environment.EnvironmentConstants
import com.wappiter.app.infrastucture.di.data.EndpointLogin
import com.wappiter.app.infrastucture.di.data.RetrofitLog
import com.wappiter.data.base.ServerInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by fran on 4/8/17.
 */
@Module
class RetrofitModule {
    @Provides
    @Singleton
    @EndpointLogin
    fun provideLoginEndpoint(): String {
        return EnvironmentConstants.DOMAIN_API
    }

    @Provides
    @Singleton
    @RetrofitLog
    fun provideRetrofitLog(): Boolean {
        return BuildConfig.RETROFIT_LOG
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
                .setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
    }

    @Provides
    @Singleton
    fun provideHeadersInterceptor(application: GlobalApplication?): ServerInterceptor {
        return ServerInterceptor(application)
    }

    @Provides
    @Singleton
    fun provideOkClient(@RetrofitLog retrofitLog: Boolean,
                        serverInterceptor: ServerInterceptor,
                        loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        val interceptors = httpClient.interceptors()
        interceptors.add(serverInterceptor)
        if (retrofitLog) {
            interceptors.add(loggingInterceptor)
        }
        httpClient.readTimeout(3, TimeUnit.MINUTES).connectTimeout(3, TimeUnit.MINUTES)
        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun providesRetrofit(@EndpointLogin endpoint: String,
                         gsonConverterFactory: GsonConverterFactory,
                         okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(endpoint).client(okHttpClient).addConverterFactory(gsonConverterFactory)
                .build()
    }
}