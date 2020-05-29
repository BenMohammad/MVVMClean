package com.domain.repositories

import com.domain.entities.MarvelCharacter
import com.domain.utils.Result

interface MarvelCharacterRepository {

    fun getCharacterById(id: Int, getFromRemote: Boolean): Result<MarvelCharacter>
}