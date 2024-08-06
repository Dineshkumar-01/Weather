package com.example.celcious

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.celcious.databinding.ActivityHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.PrivateKey

class Home : AppCompatActivity() {
    private val binding:ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        fetchWeatherData()

    }

    private fun fetchWeatherData() {
        val retrofit= Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .build().create(apiinterface::class.java)
        val response =retrofit.getWeatherData("Tiruchirappalli","09ce3e9e7944905646b9080fe836c8c8","metric")
        response.enqueue(object: Callback<WeatherApp> {
            override fun onResponse(call: Call<WeatherApp>, response: Response<WeatherApp>) {
                val responsebody = response.body()
                if (response.isSuccessful && responsebody != null) {
                    val temperature = responsebody.main.temp.toString()
                    binding.degree.text="$temperature °C"
                    //Log.d("TAG", "onResponse: $temperature")
                }
            }

            override fun onFailure(call: Call<WeatherApp>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}