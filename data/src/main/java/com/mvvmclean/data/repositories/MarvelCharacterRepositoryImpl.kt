package com.mvvmclean.data.repositories

import com.domain.entities.MarvelCharacter
import com.domain.repositories.MarvelCharacterRepository
import com.domain.utils.Result
import com.mvvmclean.data.MarvelRequestGenerator
import com.mvvmclean.data.ZERO
import com.mvvmclean.data.api.MarvelApi
import com.mvvmclean.data.database.MarvelCharacterRealm
import com.mvvmclean.data.mapper.CharacterMapperLocal
import com.mvvmclean.data.mapper.CharacterMapperService
import io.realm.Realm

class MarvelCharacterRepositoryImpl : MarvelCharacterRepository {

    private val api: MarvelRequestGenerator = MarvelRequestGenerator()
    private val mapper: CharacterMapperService = CharacterMapperService()

    override fun getCharacterById(id: Int, getFromRemote: Boolean): Result<MarvelCharacter> {
        if(getFromRemote) {
            val callResponse = api.createService(MarvelApi::class.java).getCharacterById(id)
            val response = callResponse.execute()
            if (response.isSuccessful) {
                response.body()?.data?.characters?.get(ZERO)?.let { mapper.transform(it) }
                    ?.let { return Result.Success(it) }
            }
            return Result.Failure(Exception(response.message()))
        }else {
            val mapper = CharacterMapperLocal()
            val realm = Realm.getDefaultInstance()
            val character = realm.where(MarvelCharacterRealm::class.java).equalTo("id", id).findFirst()
            character?.let {
                return Result.Success(mapper.transform(character))
            }
            return Result.Failure(Exception("Character not found"))
        }
        }
}