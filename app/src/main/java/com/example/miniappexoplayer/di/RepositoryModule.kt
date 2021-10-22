package com.example.miniappexoplayer.di

import com.example.miniappexoplayer.network.MoviesApiInterface
import com.example.miniappexoplayer.repository.MoviesRepository
import com.example.miniappexoplayer.repository.MoviesRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    fun provideRepository(api: MoviesApiInterface): MoviesRepository {
        return MoviesRepositoryImpl(api)
    }
    single { provideRepository(get()) }
}