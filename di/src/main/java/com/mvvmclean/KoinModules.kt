package com.mvvmclean


import com.domain.repositories.MarvelCharacterRepository
import com.mvvmclean.data.repositories.MarvelCharacterRepositoryImpl
import org.koin.dsl.module

val useCasesModule = module {
    single<MarvelCharacterRepository> {MarvelCharacterRepositoryImpl()}
}