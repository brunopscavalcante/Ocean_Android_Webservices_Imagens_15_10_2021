package com.oceanbrasil.ocean_android_webservices_imagens_15_10_2021

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: PokemonService = retrofit.create(PokemonService::class.java)

        println("chamando API")
        val call = service.listPokemon()

        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        call.enqueue(object: Callback<PokemonApiResult> {

            override fun onResponse(
                call: Call<PokemonApiResult>,
                response: Response<PokemonApiResult>
            ) {
                if (response.isSuccessful) {
                    val pokemonApiResultBody = response.body()

                    tvResultado.text = ""

                    pokemonApiResultBody?.let {
                        Toast.makeText(this@MainActivity, it.count.toString(), Toast.LENGTH_LONG).show()

                        it.results.forEach { pokemon ->
                            tvResultado.append(pokemon.name + "\n")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<PokemonApiResult>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Falha na requisição", Toast.LENGTH_SHORT).show()
                TODO("Not yet implemented")
            }
        })

        val repos: Call<PokemonApiResult> = service.listPokemon()
        println(repos)


    }
}