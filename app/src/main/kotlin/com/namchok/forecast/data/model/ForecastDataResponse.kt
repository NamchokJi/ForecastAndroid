package com.namchok.forecast.data.model

import com.google.gson.annotations.SerializedName

data class ForecastDataResponse(
    @SerializedName("base")
    val base: String? = null,
    @SerializedName("clouds")
    val clouds: CloudsResponse? = null,
    @SerializedName("cod")
    val code: Int? = null,
    @SerializedName("coord")
    val coordinate: CoordinateResponse? = null,
    @SerializedName("dt")
    val dt: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("main")
    val main: MainResponse? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("rain")
    val rain: RainResponse? = null,
    @SerializedName("sys")
    val sys: SystemResponse? = null,
    @SerializedName("timezone")
    val timezone: Int? = null,
    @SerializedName("visibility")
    val visibility: Int? = null,
    @SerializedName("weather")
    val weather: List<WeatherResponse?>? = null,
    @SerializedName("wind")
    val wind: WindResponse? = null,
)

data class CloudsResponse(
    @SerializedName("all")
    val all: Int? = null,
)

data class CoordinateResponse(
    @SerializedName("lat")
    val lat: Double? = null,
    @SerializedName("lon")
    val lon: Double? = null,
)

data class MainResponse(
    @SerializedName("feels_like")
    val feels: Double? = null,
    @SerializedName("grnd_level")
    val groundLevel: Int? = null,
    @SerializedName("humidity")
    val humidity: Int? = null,
    @SerializedName("pressure")
    val pressure: Int? = null,
    @SerializedName("sea_level")
    val seaLevel: Int? = null,
    @SerializedName("temp")
    val temp: Double? = null,
    @SerializedName("temp_max")
    val tempMax: Double? = null,
    @SerializedName("temp_min")
    val tempMin: Double? = null,
)

data class RainResponse(
    @SerializedName("1h")
    val hour: Double? = null,
)

data class SystemResponse(
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("sunrise")
    val sunrise: Int? = null,
    @SerializedName("sunset")
    val sunset: Int? = null,
    @SerializedName("type")
    val type: Int? = null,
)

data class WeatherResponse(
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("icon")
    val icon: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("main")
    val main: String? = null,
)

data class WindResponse(
    @SerializedName("deg")
    val deg: Int? = null,
    @SerializedName("gust")
    val gust: Double? = null,
    @SerializedName("speed")
    val speed: Double? = null,
)
