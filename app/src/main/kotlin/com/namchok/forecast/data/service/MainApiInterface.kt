package com.namchok.forecast.data.service

import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface MainApiInterface {
    @GET("data/2.5/weather")
    suspend fun getCityForecast(
        @Query("q") city: String,
    ): Response<BaseResponseModel<List<HomeDataResponse>>>
}