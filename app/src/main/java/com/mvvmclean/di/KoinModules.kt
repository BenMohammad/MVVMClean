package com.mvvmclean.di

import com.domain.repositories.MarvelCharacterRepositoryContract
import com.mvvmclean.data.FindCharacterImpl
import com.mvvmclean.data.repositories.MarvelCharacterRepository
import com.mvvmclean.viewmodels.CharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCasesModule = module {
    single<MarvelCharacterRepositoryContract> { MarvelCharacterRepository() }

}


    val viewModelModule = module {
        viewModel { CharacterViewModel() }
    }
