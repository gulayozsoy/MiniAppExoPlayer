package com.example.miniappexoplayer

import android.app.Application
import com.example.miniappexoplayer.di.apiModule
import com.example.miniappexoplayer.di.networkModule
import com.example.miniappexoplayer.di.repositoryModule
import com.example.miniappexoplayer.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MovieApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MovieApp)
            modules(
                listOf(
                    apiModule,
                    viewModelModule,
                    repositoryModule,
                    networkModule
                )
            )
        }
    }
}