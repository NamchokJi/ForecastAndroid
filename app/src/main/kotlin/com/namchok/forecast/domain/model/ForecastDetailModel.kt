package com.namchok.forecast.domain.model

data class ForecastDetailModel(
    val base: String = "",
    val dt: String = "",
    val main: MainModel = MainModel(),
    val name: String = "",
    val weather: WeatherModel = WeatherModel(),
    val wind: WindModel = WindModel(),
)

data class MainModel(
    val humidity: String = "",
    val pressure: String = "",
    val temp: String = "",
    val tempMax: String = "",
    val tempMin: String = "",
)

data class WeatherModel(
    val icon: String = "",
    val main: String = "",
)

data class WindModel(
    val speed: String = "",
)
