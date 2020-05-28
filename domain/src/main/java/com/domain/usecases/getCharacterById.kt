package com.domain.usecases

import com.domain.repositories.MarvelCharacterRepositoryContract
import org.koin.core.KoinComponent
import org.koin.core.inject

class getCharacterById : KoinComponent {

    private val marvelCharacterRepository: MarvelCharacterRepositoryContract by inject()

    suspend fun invoke(id: Int) = marvelCharacterRepository.getCharacterById(id)
}