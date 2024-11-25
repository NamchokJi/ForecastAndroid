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

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Compress
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.namchok.forecast.domain.model.ForecastDetailModel
import com.namchok.forecast.ui.theme.Typography
import com.namchok.forecast.ui.viewmodel.BaseUiState
import com.namchok.forecast.ui.viewmodel.MainViewModel
import com.namchok.forecastd.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeMainScreen() {
    val viewModel = koinViewModel<MainViewModel>()
    viewModel.initDefault()
    val uiState by viewModel.uiState.collectAsState()

    HomeScreen(
        uiState = uiState,
        onSearch = {
            viewModel.search(it)
        },
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: BaseUiState<ForecastDetailModel>,
    onSearch: (String) -> Unit,
) {
    var search by remember { mutableStateOf("") }
    val listColors = listOf(Color(0xFF62cff4), Color(0xFF2c67f2))

    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(Brush.verticalGradient(listColors))
                    .padding(24.dp),
        ) {
            Column {
                SearchView(
                    search = search,
                    onValueChange = {
                        search = it
                    },
                    onSearch = onSearch,
                )
                Box(Modifier.padding(vertical = 24.dp)) {
                    when (uiState) {
                        is BaseUiState.Loading -> {
                            LoadingScreen()
                        }

                        is BaseUiState.Success -> {
                            if (uiState.data != null) {
                                DetailScreen(uiState.data)
                            } else {
                                BaseScreen()
                            }
                        }

                        is BaseUiState.Error -> {
                            ErrorScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@Composable
private fun ErrorScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = Icons.Filled.Error,
            "",
            modifier = Modifier.size(200.dp),
            tint = Color.White,
        )
        Text(
            text = stringResource(id = R.string.label_error_screen),
            style = Typography.titleSmall,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun BaseScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            "",
            modifier = Modifier.size(200.dp),
            tint = Color.White,
        )
        Text(
            text = stringResource(id = R.string.label_base_screen),
            style = Typography.titleSmall,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun DetailScreen(data: ForecastDetailModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        Text(
            text = data.name,
            style = Typography.titleMedium,
        )
        AsyncImage(
            model =
                ImageRequest.Builder(LocalContext.current)
                    .data(
                        String.format(
                            stringResource(id = R.string.url_icon_weather),
                            data.weather.icon,
                        ),
                    )
                    .build(),
            contentDescription = null,
            placeholder = painterResource(R.drawable.ic_cloud_place_holder),
            error = painterResource(R.drawable.ic_load_image_error),
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(200.dp),
        )
        Text(
            text = data.dt,
            style = Typography.titleSmall,
        )
        Text(
            text = data.weather.main,
            style = Typography.titleSmall,
        )
        Text(
            text =
                String.format(
                    stringResource(id = R.string.label_temp_celcius),
                    data.main.temp,
                ),
            style = Typography.titleLarge,
        )
        HorizontalDivider()
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            SubDetailView(
                data =
                    String.format(
                        stringResource(id = R.string.label_wind_speed_value),
                        data.wind.speed,
                    ),
                icon = Icons.Filled.Air,
                type = stringResource(R.string.label_wind_speed_title),
            )
            SubDetailView(
                data =
                    String.format(
                        stringResource(id = R.string.label_humid_value),
                        data.main.humidity,
                    ),
                icon = Icons.Filled.WaterDrop,
                type = stringResource(R.string.label_humid_title),
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            SubDetailView(
                data =
                    String.format(
                        stringResource(id = R.string.label_temp_ranged_value),
                        data.main.tempMin,
                        data.main.tempMax,
                    ),
                icon = Icons.Filled.Thermostat,
                type = stringResource(R.string.label_temp_ranged_title),
            )
            SubDetailView(
                data =
                    String.format(
                        stringResource(id = R.string.label_pressure_value),
                        data.main.pressure,
                    ),
                icon = Icons.Filled.Compress,
                type = stringResource(R.string.label_pressure_title),
            )
        }
    }
}

@Composable
private fun SubDetailView(
    data: String,
    icon: ImageVector,
    type: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(48.dp),
            imageVector = icon,
            contentDescription = "",
            tint = Color.White,
        )
        Column {
            Text(
                text = type,
                style = Typography.bodyLarge,
            )
            Text(
                text = data,
                style = Typography.bodyLarge,
            )
        }
    }
}

@Composable
fun SearchView(
    search: String,
    onValueChange: (String) -> Unit,
    onSearch: (String) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(Color.White),
    ) {
        OutlinedTextField(
            value = search,
            onValueChange = onValueChange,
            label = null,
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            textStyle = LocalTextStyle.current.copy(color = Color.Black),
            placeholder = { Text(text = "Enter city name.", color = Color.Black) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions =
                KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                        onSearch.invoke(search)
                    },
                ),
        )
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    HomeScreen(
        uiState =
            BaseUiState.Success(
                data =
                    ForecastDetailModel(
                        name = "London",
                    ),
            ),
        onSearch = {},
    )
}
