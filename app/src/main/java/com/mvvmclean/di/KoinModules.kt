package com.mvvmclean.di

import com.mvvmclean.viewmodels.CharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelsModule = module {
    viewModel { CharacterViewModel() }

}

