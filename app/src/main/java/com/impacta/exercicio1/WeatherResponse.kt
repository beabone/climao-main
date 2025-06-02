package com.impacta.exercicio1

data class WeatherResponse(
    val name: String, // nome da cidade
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind
)

data class Main(
    val temp: Float,
    val humidity: Int
)

data class Weather(
    val description: String
)

data class Wind(
    val speed: Float
)