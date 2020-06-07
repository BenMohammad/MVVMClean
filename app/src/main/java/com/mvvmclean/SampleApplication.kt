package com.mvvmclean

import android.app.Application
import com.mvvmclean.di.useCasesModule

import com.mvvmclean.di.viewModelsModule
import io.realm.Realm
import org.koin.core.context.startKoin

class SampleApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        startKoin { modules(listOf(repositoriesModule, viewModelsModule, useCasesModule))}
    }
}