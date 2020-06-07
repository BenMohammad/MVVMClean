package com.mvvmclean


import com.domain.repositories.MarvelCharacterRepository
import com.mvvmclean.data.database.CharacterDatabase
import com.mvvmclean.data.repositories.MarvelCharacterRepositoryImpl
import com.mvvmclean.data.service.CharacterService
import org.koin.dsl.module

val repositoriesModule = module {

    single{ CharacterService()}
    single{ CharacterDatabase()}
    single<MarvelCharacterRepository> {MarvelCharacterRepositoryImpl(get(), get())}

}