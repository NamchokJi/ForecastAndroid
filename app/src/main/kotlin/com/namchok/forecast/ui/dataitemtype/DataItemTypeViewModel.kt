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

package com.namchok.forecast.ui.dataitemtype

import androidx.lifecycle.ViewModel
import com.namchok.forecast.data.repository.MainApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DataItemTypeViewModel
    @Inject
    constructor(
        private val mainApiRepository: MainApiRepository,
    ) : ViewModel() {
//        val uiState: StateFlow<DataItemTypeUiState> =
//            dataItemTypeRepository
//                .dataItemTypes.map<List<String>, DataItemTypeUiState>(::Success)
//                .catch { emit(Error(it)) }
//                .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)
//
//        fun addDataItemType(name: String) {
//            viewModelScope.launch {
//                dataItemTypeRepository.add(name)
//            }
//        }
    }

sealed interface DataItemTypeUiState {
    object Loading : DataItemTypeUiState

    data class Error(val throwable: Throwable) : DataItemTypeUiState

    data class Success(val data: List<String>) : DataItemTypeUiState
}
