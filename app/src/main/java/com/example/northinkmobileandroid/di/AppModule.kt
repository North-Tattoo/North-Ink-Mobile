package com.example.northinkmobileandroid.di

import android.content.Context
import android.content.SharedPreferences
import com.example.northinkmobileandroid.viewmodel.TatuadorViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { TatuadorViewModel() }
    single { SessaoUsuario(0L) }
}