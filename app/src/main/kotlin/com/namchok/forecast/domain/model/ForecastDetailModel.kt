package com.namchok.forecast.domain.model

data class ForecastDetailModel(
    val base: String = "",
    val clouds: CloudsModel = CloudsModel(),
    val code: Int = 0,
    val coordinate: CoordinateModel = CoordinateModel(),
    val dt: Int = 0,
    val id: Int = 0,
    val main: MainModel = MainModel(),
    val name: String = "",
    val rain: RainModel = RainModel(),
    val sys: SystemModel = SystemModel(),
    val timezone: Int = 0,
    val visibility: Int = 0,
    val weather: MutableList<WeatherModel> = mutableListOf(),
    val wind: WindModel = WindModel(),
)

data class CloudsModel(
    val all: Int = 0,
)

data class CoordinateModel(
    val lat: Double = 0.0,
    val lon: Double = 0.0,
)

data class MainModel(
    val feels: Double = 0.0,
    val groundLevel: Int = 0,
    val humidity: Int = 0,
    val pressure: Int = 0,
    val seaLevel: Int = 0,
    val temp: Double = 0.0,
    val tempMax: Double = 0.0,
    val tempMin: Double = 0.0,
)

data class RainModel(
    val hour: Double = 0.0,
)

data class SystemModel(
    val country: String = "",
    val id: Int = 0,
    val sunrise: Int = 0,
    val sunset: Int = 0,
    val type: Int = 0,
)

data class WeatherModel(
    val description: String = "",
    val icon: String = "",
    val id: Int = 0,
    val main: String = "",
)

data class WindModel(
    val deg: Int = 0,
    val gust: Double = 0.0,
    val speed: Double = 0.0,
)
