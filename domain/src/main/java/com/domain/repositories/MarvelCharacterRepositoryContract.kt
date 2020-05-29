package com.domain.repositories

import com.domain.entities.MarvelCharacter
import com.domain.utils.Result

interface MarvelCharacterRepositoryContract {

    fun getCharacterById(id: Int): Result<MarvelCharacter>
}