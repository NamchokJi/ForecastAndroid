@file:OptIn(ExperimentalCoroutinesApi::class)

package com.namchok.forecast.ui

import app.cash.turbine.test
import com.namchok.forecast.BaseUnitTest
import com.namchok.forecast.domain.model.ForecastDetailModel
import com.namchok.forecast.domain.usecase.MappingForecastDetailUseCase
import com.namchok.forecast.ui.viewmodel.BaseUiState
import com.namchok.forecast.ui.viewmodel.MainViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertTrue

class MainViewModelTest : BaseUnitTest() {
    private lateinit var viewModel: MainViewModel
    private val useCase: MappingForecastDetailUseCase = mockk()

    override fun setupTest() {
        super.setupTest()
        viewModel =
            MainViewModel(
                useCase,
            )
    }

    @Test
    fun `search city by name success then update ui state`() =
        runTest {
            // arrange
            val mockkResponse =
                ForecastDetailModel(
                    name = "city",
                    dt = "1732514774",
                )

            coEvery {
                useCase.execute(any())
            } returns
                flowOf(
                    mockkResponse,
                )

            // act
            viewModel.search("city")
            advanceUntilIdle()

            // assert
            viewModel.uiState.test {
                val updatedState = awaitItem()
                assertTrue(updatedState is BaseUiState.Success)
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun `search city by name failed then update ui state`() =
        runTest {
            // arrange
            coEvery {
                useCase.execute(any())
            } returns
                flow {
                    throw Exception()
                }
            // act
            viewModel.search("city")
            advanceUntilIdle()

            // assert
            viewModel.uiState.test {
                val updatedState = awaitItem()
                assertTrue(updatedState is BaseUiState.Error)
                cancelAndConsumeRemainingEvents()
            }
        }
}
