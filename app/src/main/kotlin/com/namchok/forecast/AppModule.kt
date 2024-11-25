package com.namchok.forecast

import com.namchok.forecast.data.di.dataModule
import com.namchok.forecast.domain.di.domainModule
import com.namchok.forecast.ui.di.presentationModule
import org.koin.dsl.module

val appModule =
    module {
        includes(dataModule)
        includes(domainModule)
        includes(presentationModule)
    }
