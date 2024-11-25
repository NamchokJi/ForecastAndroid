package com.namchok.forecast

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
open class BaseUnitTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    val testDispatcher = UnconfinedTestDispatcher()

    @Before
    open fun setupTest() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    open fun tearDown() {
        clearAllMocks()
        Dispatchers.resetMain()
    }
}

// class TestDispatchers : DispatcherProvider {
//    val testDispatcher = UnconfinedTestDispatcher()
//    override val main: CoroutineDispatcher
//        get() = testDispatcher
//    override val io: CoroutineDispatcher
//        get() = testDispatcher
//    override val default: CoroutineDispatcher
//        get() = testDispatcher
// }
