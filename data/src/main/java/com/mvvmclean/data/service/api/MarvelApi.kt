package com.mvvmclean.data.service.api

import com.mvvmclean.data.database.response.DatabaseResponse
import com.mvvmclean.data.service.response.CharacterResponse
import com.mvvmclean.data.service.response.MarvelBaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelApi {

    @GET("/v1/public/characters/{characterId}")
    fun getCharacterById(@Path("characterId") id: Int): Call<MarvelBaseResponse<DatabaseResponse<ArrayList<CharacterResponse>>>>
}