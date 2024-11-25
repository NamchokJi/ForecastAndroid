package com.namchok.forecast.domain.usecase

import com.namchok.forecast.data.model.ForecastDataResponse
import com.namchok.forecast.data.repository.MainApiRepository
import com.namchok.forecast.domain.model.ForecastDetailModel
import com.namchok.forecast.domain.model.MainModel
import com.namchok.forecast.domain.model.WeatherModel
import com.namchok.forecast.domain.model.WindModel
import com.namchok.forecast.domain.utils.getDateTime
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf

class MappingForecastDetailUseCase(
    private val repository: MainApiRepository,
) {
    companion object {
        const val UNKNOWN = "n/a"
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun execute(city: String): Flow<ForecastDetailModel> =
        repository.getCityForecast(city = city).flatMapMerge { response ->
            flowOf(mappingForecastDetail(response))
        }

    private fun mappingForecastDetail(response: ForecastDataResponse): ForecastDetailModel {
        return ForecastDetailModel(
            base = response.base.orEmpty(),
            main =
                MainModel(
                    temp = (response.main?.temp ?: UNKNOWN).toString(),
                    humidity = (response.main?.humidity ?: UNKNOWN).toString(),
                    tempMax = (response.main?.tempMax ?: UNKNOWN).toString(),
                    tempMin = (response.main?.tempMin ?: UNKNOWN).toString(),
                    pressure = (response.main?.pressure ?: UNKNOWN).toString(),
                ),
            weather =
                response.weather?.firstOrNull()?.let {
                    WeatherModel(
                        main = it.main.orEmpty(),
                        icon = it.icon.orEmpty(),
                    )
                } ?: WeatherModel(),
            wind =
                WindModel(
                    speed = (response.wind?.speed ?: UNKNOWN).toString(),
                ),
            name = response.name.orEmpty(),
            dt = response.dt?.getDateTime() ?: UNKNOWN,
        )
    }
}
