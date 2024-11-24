package com.namchok.forecast.domain.usecase

import com.namchok.forecast.data.model.ForecastDataResponse
import com.namchok.forecast.data.repository.MainApiRepository
import com.namchok.forecast.domain.model.ForecastDetailModel
import com.namchok.forecast.domain.model.MainModel
import com.namchok.forecast.domain.model.WindModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf

class MappingForecastDetailUseCase(
    private val repository: MainApiRepository,
) {
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
                    temp = response.main?.temp ?: 0.0,
                    humidity = response.main?.humidity ?: 0,
                ),
            weather = mutableListOf(),
            wind =
                WindModel(
                    speed = response.wind?.speed ?: 0.0,
                ),
            name = response.name.orEmpty(),
            timezone = response.timezone ?: 0,
        )
    }
}
