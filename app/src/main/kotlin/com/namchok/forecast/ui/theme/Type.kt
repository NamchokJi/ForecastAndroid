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

package com.namchok.forecast.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.namchok.forecastd.R

private val font =
    FontFamily(
        Font(R.font.nunito_regular, FontWeight.Normal),
    )

private val baseTypography =
    TextStyle(
        fontFamily = font,
        letterSpacing = 0.5.sp,
        color = Color.White,
    )

val Typography =
    Typography(
        bodyLarge =
            baseTypography.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            ),
        titleLarge =
            baseTypography.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 72.sp,
            ),
        titleMedium =
            baseTypography.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 48.sp,
            ),
        titleSmall =
            baseTypography.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
            ),
        headlineLarge = baseTypography.copy(
            fontWeight = FontWeight.Bold,
            fontSize = 124.sp,
        ),
    )
