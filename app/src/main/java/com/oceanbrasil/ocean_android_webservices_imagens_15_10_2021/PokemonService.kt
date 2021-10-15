package com.oceanbrasil.ocean_android_webservices_imagens_15_10_2021

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon")
    fun listPokemon(): Call<PokemonApiResult>
}