package com.example.miniappexoplayer.di

import com.example.miniappexoplayer.network.MoviesApiInterface
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    fun provideApi(retrofit: Retrofit): MoviesApiInterface {
        return retrofit.create( MoviesApiInterface::class.java)
    }
    single { provideApi(get()) }

}