package com.namchok.forecast.data.repository

import com.namchok.forecast.data.model.ForecastDataResponse
import kotlinx.coroutines.flow.Flow

interface MainApiRepository {
    fun getCityForecast(city: String): Flow<ForecastDataResponse>
}
