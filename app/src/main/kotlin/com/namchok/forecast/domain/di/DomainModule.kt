package com.namchok.forecast.domain.di

import com.namchok.forecast.domain.usecase.MappingForecastDetailUseCase
import org.koin.dsl.module

val domainModule =
    module {
        single { MappingForecastDetailUseCase(get()) }
    }
