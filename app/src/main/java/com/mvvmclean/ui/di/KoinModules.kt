package com.mvvmclean.ui.di

import com.mvvmclean.data.repositories.MarvelCharacterRepositoryImpl
import com.mvvmclean.ui.viewmodels.CharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

    val useCasesModule = module {
    single<MarvelCharacterRepositoryImpl> { MarvelCharacterRepositoryImpl() }

    }

    val viewModelModule = module {
    viewModel { CharacterViewModel() }
    }