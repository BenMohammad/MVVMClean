package com.mvvmclean.data.repositories

import com.domain.entities.MarvelCharacter
import com.domain.repositories.MarvelCharacterRepository
import com.domain.utils.Result
import com.mvvmclean.data.database.CharacterDatabase
import com.mvvmclean.data.service.CharacterService

class MarvelCharacterRepositoryImpl : MarvelCharacterRepository {



    override fun getCharacterById(id: Int, getFromRemote: Boolean): Result<MarvelCharacter> {
        return if(getFromRemote) {
            when(val marvelCharacterResult: Result<MarvelCharacter> = CharacterService.getCharacterById(id)) {
                is Result.Failure -> {
                    marvelCharacterResult
                }
                is Result.Success -> {
                    insertOrUpdateCharacter(marvelCharacterResult.data)
                    marvelCharacterResult
                }
            }


        }else {
            CharacterDatabase.getCharacterById(id)
        }
        }

    private fun insertOrUpdateCharacter(character: MarvelCharacter) {

        CharacterDatabase.insertOrUpdateCharacter(character)

    }}