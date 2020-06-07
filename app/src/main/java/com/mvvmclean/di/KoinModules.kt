package com.mvvmclean.di

import com.domain.usecases.GetCharacterByIdUseCase
import com.mvvmclean.viewmodels.CharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelsModule = module {
    viewModel { CharacterViewModel(get())}
}

val useCasesModule = module {
    single { GetCharacterByIdUseCase() }
}

