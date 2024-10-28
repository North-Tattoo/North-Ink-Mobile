package com.example.northinkmobileandroid.api

import android.content.Context
import android.content.SharedPreferences
import com.example.northinkmobileandroid.di.SessaoUsuario

class AuthRepository {

    private var sessaoUsuario: SessaoUsuario? = null


    fun updateSession(novaSessao: SessaoUsuario) {
        this.sessaoUsuario = novaSessao
        // Você pode fazer aqui o que quiser com a sessão, como salvar em SharedPreferences se desejar
    }

    // Método para obter a sessão atual
    fun getSession(): SessaoUsuario? {
        return sessaoUsuario
    }

    // Se você estiver usando SharedPreferences para armazenar a sessão
    fun saveSession(sessao: SessaoUsuario, context: Context) {
        val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putLong("userId", sessao.userId)
        editor.putString("token", sessao.token)
        editor.putString("nome", sessao.nome)
        editor.putString("login", sessao.login)
        editor.apply()
    }

    // Método para carregar a sessão do SharedPreferences
    fun loadSession(context: Context) {
        val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        sessaoUsuario = SessaoUsuario(
            userId = sharedPreferences.getLong("userId", -1L),
            token = sharedPreferences.getString("token", "") ?: "",
            nome = sharedPreferences.getString("nome", "") ?: "",
            login = sharedPreferences.getString("login", "") ?: ""
        )
    }
}