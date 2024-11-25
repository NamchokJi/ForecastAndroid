@file:OptIn(ExperimentalCoroutinesApi::class)

package com.namchok.forecast.domain

import com.namchok.forecast.BaseUnitTest
import com.namchok.forecast.data.model.ForecastDataResponse
import com.namchok.forecast.data.repository.MainApiRepository
import com.namchok.forecast.domain.usecase.MappingForecastDetailUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class MappingForecastDetailUseCaseTest : BaseUnitTest() {
    private lateinit var usecase: MappingForecastDetailUseCase
    private val repository: MainApiRepository = mockk()

    override fun setupTest() {
        super.setupTest()
        usecase =
            MappingForecastDetailUseCase(
                repository,
            )
    }

    @Test
    fun `getCityForecast success then return response`() =
        runTest {
            // arrange
            val mockkResponse =
                ForecastDataResponse(
                    name = "city",
                    dt = 1732514774,
                )

            coEvery {
                repository.getCityForecast(any())
            } returns
                flowOf(
                    mockkResponse,
                )

            // act
            val flow = usecase.execute("city")

            // assert
            flow.collect { result ->
                assertEquals("city", result.name)
                assertEquals("25 November 2024", result.dt)
                assertEquals("n/a", result.main.temp)
                assertEquals("n/a", result.main.tempMax)
                assertEquals("n/a", result.main.tempMin)
            }
        }
}
