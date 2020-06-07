package com.mvvmclean


import com.domain.repositories.MarvelCharacterRepository
import com.mvvmclean.data.repositories.MarvelCharacterRepositoryImpl
import org.koin.dsl.module

val repositoriesModule = module {
    single<MarvelCharacterRepository> {MarvelCharacterRepositoryImpl()}
}