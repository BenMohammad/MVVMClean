package com.mvvmclean.data.repositories

import com.domain.entities.MarvelCharacter
import com.domain.repositories.MarvelCharacterRepositoryContract

class MarvelCharacterRepository : MarvelCharacterRepositoryContract {

    override fun getCharacterById(id: Int): MarvelCharacter {
        return MarvelCharacter(1, "Hola", "Hola")
    }
}