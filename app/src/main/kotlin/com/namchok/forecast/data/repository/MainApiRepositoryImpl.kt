package com.namchok.forecast.data.repository

import com.namchok.forecast.data.model.ForecastDataResponse
import com.namchok.forecast.data.service.MainApiInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainApiRepositoryImpl(
    private val service: MainApiInterface,
) : MainApiRepository {
    override fun getCityForecast(city: String): Flow<ForecastDataResponse> =
        flow {
            val response = service.getCityForecast(city = city)
            if (response.isSuccessful) {
                response.body()?.let { body ->
                    emit(body)
                } ?: run {
                    throw IllegalStateException("Response body is null")
                }
            } else {
                throw IllegalStateException("Response is failure")
            }
        }
}
