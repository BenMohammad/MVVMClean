package com.mvvmclean.data.repositories

import com.domain.entities.MarvelCharacter
import com.domain.repositories.MarvelCharacterRepository
import com.domain.utils.Result
import com.mvvmclean.data.database.CharacterDatabase
import com.mvvmclean.data.service.CharacterService

class MarvelCharacterRepositoryImpl(private val characterService: CharacterService,
                                    private val characterDatabase: CharacterDatabase) : MarvelCharacterRepository {



    override fun getCharacterById(id: Int, getFromRemote: Boolean): Result<MarvelCharacter> =
        if(getFromRemote) {
            val marvelCharacterResult = characterService.getCharacterById(id)
            if(marvelCharacterResult is Result.Success) {
                insertOrUpdateCharacter(marvelCharacterResult.data)
            }
            marvelCharacterResult
        } else {
            characterDatabase.getCharacterById(id)

    }

    private fun insertOrUpdateCharacter(character: MarvelCharacter) {
        characterDatabase.insertOrUpdateCharacter(character)
    }
}