@file:OptIn(ExperimentalCoroutinesApi::class)

package com.namchok.forecast.data

import com.namchok.forecast.BaseUnitTest
import com.namchok.forecast.data.model.ForecastDataResponse
import com.namchok.forecast.data.repository.MainApiRepository
import com.namchok.forecast.data.repository.MainApiRepositoryImpl
import com.namchok.forecast.data.service.MainApiInterface
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertThrows
import org.junit.Test
import retrofit2.Response

class MainApiRepositoryTest : BaseUnitTest() {
    private lateinit var repository: MainApiRepository
    private val services: MainApiInterface = mockk()

    override fun setupTest() {
        super.setupTest()
        repository =
            MainApiRepositoryImpl(
                services,
            )
    }

    @Test
    fun `getCityForecast success then return response`() =
        runTest {
            // arrange
            val mockkResponse = ForecastDataResponse()

            coEvery {
                services.getCityForecast(any())
            } returns
                Response.success(
                    mockkResponse,
                )

            // act
            val result = repository.getCityForecast("bangkok")

            // assert
            result.collectLatest {
                Assert.assertEquals(it, mockkResponse)
            }
        }

    @Test
    fun `getCityForecast fail then throw error exception`() =
        runTest {
            // arrange
            val error = Exception("test error")
            coEvery { services.getCityForecast(any()) } throws error

            // assert
            assertThrows(Exception::class.java) {
                runTest {
                    repository.getCityForecast("")
                }
            }
        }

    @Test
    fun `getCityForecast success when response body is null then return response`() =
        runTest {
            // arrange
            val mockkResponse = ForecastDataResponse()

            coEvery {
                services.getCityForecast(any())
            } returns
                Response.success(
                    null,
                )

            // assert
            assertThrows(Exception::class.java) {
                runTest {
                    repository.getCityForecast("")
                }
            }
        }
}
