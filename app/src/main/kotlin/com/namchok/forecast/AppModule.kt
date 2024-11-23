package com.namchok.forecast

import com.namchok.forecast.data.di.dataModule
import org.koin.dsl.module

val appModule =
    module {
        includes(dataModule)
    }
