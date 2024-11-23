package com.namchok.forecast.data.di

import com.namchok.forecast.data.repository.MainApiRepository
import com.namchok.forecast.data.repository.MainApiRepositoryImpl
import com.namchok.forecast.data.service.MainApiInterface
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule =
    module {
        includes(networkModule)
        factory { provideMainService(get()) }
        single<MainApiRepository> { MainApiRepositoryImpl(get()) }
    }

private fun provideMainService(retrofit: Retrofit): MainApiInterface {
    return retrofit.create(MainApiInterface::class.java)
}
