package com.mvvmclean.data.repositories

import com.domain.entities.MarvelCharacter
import com.domain.repositories.MarvelCharacterRepository
import com.domain.utils.Result
import com.mvvmclean.data.database.CharacterDatabase
import com.mvvmclean.data.mapper.CharacterMapperLocal
import com.mvvmclean.data.service.CharacterService
import io.realm.Realm

class MarvelCharacterRepositoryImpl : MarvelCharacterRepository {



    override fun getCharacterById(id: Int, getFromRemote: Boolean): Result<MarvelCharacter> {
        if(getFromRemote) {
            val marvelCharacterResult: Result<MarvelCharacter> = CharacterService.getCharacterById(id)

            when(marvelCharacterResult) {
                is Result.Failure -> {
                    return marvelCharacterResult
                }
                is Result.Success -> {
                    insertOrUpdateCharacter(marvelCharacterResult.data)
                    return marvelCharacterResult
                }
            }


        }else {
            return CharacterDatabase.getCharacterById(id)
        }
        }

    private fun insertOrUpdateCharacter(character: MarvelCharacter) {

        CharacterDatabase.insertOrUpdateCharacter(character)

    }}