package com.namchok.forecast.data.di

import android.content.Context
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.namchok.forecast.data.interceptor.AuthenticatorInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule =
    module {
        single { provideAuthenticatorInterceptor() }
        single { provideLoggingInterceptor() }

        single {
            provideOkHttpClient(
                loggingInterceptor = get(),
                authenticatorInterceptor = get(),
                chuckerInterceptor = get(),
            )
        }

        single { debugCollector(androidContext()) }
        single { debugInterceptor(androidContext()) }
        factory { provideRetrofit(get()) }
    }

private const val BASE_TIME_OUT = 30L

private fun provideAuthenticatorInterceptor(): AuthenticatorInterceptor = AuthenticatorInterceptor()

private fun provideLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor { message ->
        Log.e("RESPONSE_OKHTTP", message)
    }

private fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
    authenticatorInterceptor: AuthenticatorInterceptor,
    chuckerInterceptor: ChuckerInterceptor,
): OkHttpClient {
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    val okHttpClientBuilder =
        OkHttpClient.Builder()
            .readTimeout(BASE_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(BASE_TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(BASE_TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor(authenticatorInterceptor)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(chuckerInterceptor)

    return okHttpClientBuilder.build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun debugInterceptor(context: Context): ChuckerInterceptor =
    ChuckerInterceptor.Builder(context)
        .collector(debugCollector(context))
        .maxContentLength(250000L)
        .redactHeaders(emptySet())
        .alwaysReadResponseBody(true)
        .build()

private fun debugCollector(context: Context) =
    ChuckerCollector(
        context = context,
        showNotification = true,
        retentionPeriod = RetentionManager.Period.ONE_HOUR,
    )
