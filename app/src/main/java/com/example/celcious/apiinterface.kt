package com.example.celcious

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
interface apiinterface {
    @GET("Weather")
    fun getWeatherData(
        @Query("q") city:String,
        @Query("appid") appid:String,
        @Query("units") units:String
        ) : Call<WeatherApp>
}