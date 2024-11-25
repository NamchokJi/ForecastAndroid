package com.namchok.forecast.ui.di

import com.namchok.forecast.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule =
    module {
        viewModelOf(::MainViewModel)
    }
