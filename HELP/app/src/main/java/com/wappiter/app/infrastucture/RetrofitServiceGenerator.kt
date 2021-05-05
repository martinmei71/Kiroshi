package com.wappiter.app.infrastucture

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.wappiter.app.BuildConfig
import com.wappiter.app.data.constants.environment.EnvironmentConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

internal object RetrofitServiceGenerator {

    private val httpClient: OkHttpClient
        get() = getUnsafeOkHttpClient().addInterceptor(HttpLoggingInterceptor()
                .setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.BASIC))
                .readTimeout(90, TimeUnit.SECONDS).writeTimeout(90, TimeUnit.SECONDS).build()

    private val gsonConverterFactory = GsonConverterFactory
            .create(GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create())

    private val builder = Retrofit.Builder().baseUrl(EnvironmentConstants.DOMAIN_API)
            .addConverterFactory(gsonConverterFactory)

    fun <S> createService(serviceClass: Class<S>): S {

        val retrofit = builder.client(httpClient).build()
        return retrofit.create(serviceClass)
    }

    private fun getUnsafeOkHttpClient(): OkHttpClient.Builder {

        try {

            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(

                    object : X509TrustManager {

                        @Throws(CertificateException::class)
                        override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>,
                                                        authType: String) {

                        }

                        @Throws(CertificateException::class)
                        override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>,
                                                        authType: String) {

                        }

                        override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {

                            return arrayOf()
                        }
                    })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)

            builder.hostnameVerifier(HostnameVerifier { p0, p1 -> true })

            return builder

        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}
