package com.namchok.forecast.data.service

import com.namchok.forecast.data.model.ForecastDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApiInterface {
    @GET("data/2.5/weather")
    suspend fun getCityForecast(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
    ): Response<ForecastDataResponse>
}
