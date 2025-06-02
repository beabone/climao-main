package com.impacta.exercicio1

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.impacta.exercicio1.WeatherService
import com.impacta.exercicio1.WeatherResponse
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class PrevisaoActivity : AppCompatActivity() {

    private lateinit var editCidade: EditText
    private lateinit var btnBuscar: Button
    private lateinit var btnVoltar: Button
    private lateinit var txtCidade: TextView
    private lateinit var txtTemperatura: TextView
    private lateinit var txtClima: TextView
    private lateinit var txtUmidade: TextView
    private lateinit var txtVento: TextView

    private val apiKey = "a6f7000819c6f6da15919ab2bf4221bc" // Substitua pela sua API KEY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_previsao)

        editCidade = findViewById(R.id.editCidade)
        btnBuscar = findViewById(R.id.btnBuscar)
        btnVoltar = findViewById(R.id.btnVoltar)
        txtCidade = findViewById(R.id.txtCidade)
        txtTemperatura = findViewById(R.id.txtTemperatura)
        txtClima = findViewById(R.id.txtClima)
        txtUmidade = findViewById(R.id.txtUmidade)
        txtVento = findViewById(R.id.txtVento)

        btnBuscar.setOnClickListener {
            val cidade = editCidade.text.toString().trim()
            if (cidade.isNotEmpty()) {
                buscarPrevisao(cidade)
            } else {
                Toast.makeText(this, "Digite uma cidade", Toast.LENGTH_SHORT).show()
            }
        }

        btnVoltar.setOnClickListener {
            finish() // Fecha essa tela e volta
        }
    }

    private fun buscarPrevisao(cidade: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WeatherService::class.java)
        val call = service.getWeatherByCity(cidade, apiKey)

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    val weather = response.body()
                    txtCidade.text = weather?.name
                    txtTemperatura.text = "${weather?.main?.temp}°C"
                    txtClima.text = "Clima: ${weather?.weather?.get(0)?.description}"
                    txtUmidade.text = "Umidade: ${weather?.main?.humidity}%"
                    txtVento.text = "Vento: ${weather?.wind?.speed} km/h"
                } else {
                    Toast.makeText(this@PrevisaoActivity, "Cidade não encontrada", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Toast.makeText(this@PrevisaoActivity, "Erro: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
