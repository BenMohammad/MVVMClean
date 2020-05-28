package com.domain.repositories

import com.domain.entities.MarvelCharacter

interface MarvelCharacterRepositoryContract {

    fun getCharacterById(id: Int): MarvelCharacter
}