package com.mvvmclean.data.api

import com.mvvmclean.data.service.response.CharacterResponse
import com.mvvmclean.data.service.response.DataBaseResponse
import com.mvvmclean.data.service.response.MarvelBaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelApi {

    @GET("/v1/public/characters/{characterId}")
    fun getCharacterById(@Path("characterId") id: Int): Call<MarvelBaseResponse<DataBaseResponse<ArrayList<CharacterResponse>>>>
}