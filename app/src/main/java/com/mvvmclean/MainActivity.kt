package com.mvvmclean

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.di.useCaseModule
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startKoin { modules(useCaseModule) }
    }
}
