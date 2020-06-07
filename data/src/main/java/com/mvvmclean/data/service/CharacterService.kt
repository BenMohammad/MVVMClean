package com.mvvmclean.data.service

import com.domain.entities.MarvelCharacter
import com.domain.utils.Result
import com.mvvmclean.data.MarvelRequestGenerator
import com.mvvmclean.data.ZERO
import com.mvvmclean.data.service.api.MarvelApi
import com.mvvmclean.data.mapper.CharacterMapperService
import java.lang.Exception

class CharacterService {

    companion object {
        private val api: MarvelRequestGenerator = MarvelRequestGenerator()
        private val mapper: CharacterMapperService = CharacterMapperService()

        fun getCharacterById(id: Int): Result<MarvelCharacter> {
            val callResponse = api.createService(MarvelApi::class.java).getCharacterById(id)
            val response = callResponse.execute()

            if (response != null) {
                if (response.isSuccessful) {
                    response.body()?.data?.characters?.get(ZERO)?.let {
                        mapper.transform(it)
                    }?.let {
                        return Result.Success(it)
                    }}
                    return Result.Failure(Exception(response.message()))

                }
            return Result.Failure(Exception("Bad request/response"))
            }
        }

    }