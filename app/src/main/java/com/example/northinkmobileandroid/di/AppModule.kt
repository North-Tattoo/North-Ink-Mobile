package com.example.northinkmobileandroid.di

import android.content.Context
import android.content.SharedPreferences
import com.example.northinkmobileandroid.api.AuthRepository
import com.example.northinkmobileandroid.viewmodel.TatuadorViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AuthRepository() } // Provê uma única instância de AuthRepository
    viewModel { TatuadorViewModel() }
    single { SessaoUsuario(0L) }
}