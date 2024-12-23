/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.namchok.forecast.ui.viewmodel

import androidx.lifecycle.viewModelScope
import com.namchok.forecast.domain.model.ForecastDetailModel
import com.namchok.forecast.domain.usecase.MappingForecastDetailUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class MainViewModel(
    val usecase: MappingForecastDetailUseCase,
) : BaseViewModel<ForecastDetailModel>() {
    fun search(city: String) {
        usecase.execute(city = city)
            .onStart {
                setUiStateLoading()
            }
            .onEach { result ->
                setContent(result)
            }
            .catch {
                setUiStateError(BaseErrorType.API_ERROR)
            }
            .launchIn(viewModelScope)
    }
}
