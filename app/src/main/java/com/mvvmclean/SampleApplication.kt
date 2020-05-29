package com.mvvmclean

import android.app.Application
import com.mvvmclean.ui.di.useCasesModule
import com.mvvmclean.ui.di.viewModelModule
import io.realm.Realm
import org.koin.core.context.startKoin

class SampleApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        startKoin { listOf(useCasesModule, viewModelModule)}
    }
}