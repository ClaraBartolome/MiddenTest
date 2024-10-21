package com.example.middentest.core

import android.app.Application
import com.example.middentest.core.di.UseCaseModules
import com.example.middentest.core.di.dispatcherFactoryModule
import com.example.middentest.core.di.netModule
import com.example.middentest.core.di.randomUserAPIModule
import com.example.middentest.core.di.repositoryModule
import com.example.middentest.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@MyApp)
            modules(listOf(
                dispatcherFactoryModule,
                netModule,
                randomUserAPIModule,
                repositoryModule,
                UseCaseModules,
                viewModelModule
            ))
        }
    }
}