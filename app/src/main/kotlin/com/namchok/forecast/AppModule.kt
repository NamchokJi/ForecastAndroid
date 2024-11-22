package com.namchok.forecast

import com.namchok.forecast.data.di.dataModule
import com.namchok.forecast.domain.di.domainModule
import org.koin.dsl.module

val appModule =
    module {
        includes(dataModule)
        includes(domainModule)
    }
