package com.example.miniappexoplayer.di

import com.example.miniappexoplayer.ui.mainpage.MainPageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainPageViewModel(get())
    }
}