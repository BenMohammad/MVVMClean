package com.domain.usecases

import com.domain.repositories.MarvelCharacterRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class GetCharacterByIdUseCase : KoinComponent {

    private val marvelCharacterRepository: MarvelCharacterRepository by inject()

    operator fun invoke(id: Int) = marvelCharacterRepository.getCharacterById(id)
}