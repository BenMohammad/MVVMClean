package com.mvvmclean.data.repositories

import com.domain.entities.MarvelCharacter
import com.domain.repositories.MarvelCharacterRepositoryContract
import com.domain.utils.Result
import com.mvvmclean.data.MarvelRequestGenerator
import com.mvvmclean.data.ZERO
import com.mvvmclean.data.api.MarvelApi
import com.mvvmclean.data.mapper.CharacterMapperService

class MarvelCharacterRepository : MarvelCharacterRepositoryContract {

    private val api: MarvelRequestGenerator = MarvelRequestGenerator()
    private val mapper: CharacterMapperService = CharacterMapperService()

    override fun getCharacterById(id: Int): Result<MarvelCharacter> {
        val callResponse = api.createService(MarvelApi::class.java).getCharacterById(id)
        val response = callResponse.execute()
        if(response.isSuccessful) {
            response.body()?.data?.characters?.get(ZERO)?.let { mapper.transform(it)}
                ?.let { return Result.Success(it)}
        }
        return Result.Failure(Exception(response.message()))
    }
}