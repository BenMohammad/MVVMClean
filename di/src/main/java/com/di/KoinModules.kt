package com.di

import com.domain.repositories.MarvelCharacterRepositoryContract
import com.mvvmclean.data.repositories.MarvelCharacterRepository
import org.koin.dsl.module

val useCaseModule = module {
    single<MarvelCharacterRepositoryContract> {MarvelCharacterRepository()}
}