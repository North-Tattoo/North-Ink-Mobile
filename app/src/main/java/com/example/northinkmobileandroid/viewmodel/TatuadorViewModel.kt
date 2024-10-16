package com.example.northinkmobileandroid.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.northinkmobileandroid.api.RetrofitInstance
import com.example.northinkmobileandroid.data.model.Estilo
import com.example.northinkmobileandroid.data.model.LoginRequest
import com.example.northinkmobileandroid.data.model.LoginResponse
import com.example.northinkmobileandroid.data.model.TatuadorCriacao
import kotlinx.coroutines.launch
import retrofit2.Response


class TatuadorViewModel : ViewModel(){

    var nome: String = ""
    var sobrenome: String = ""
    var email: String = ""
    var cpf: String = ""
    var celular: String = ""
    var senha: String = ""
    var resumo: String = ""
    var estilos: List<Estilo> = listOf()

    // Variáveis para armazenar o login
    var loginEmail: String = ""
    var loginSenha: String = ""
    var userId: Int? = null
    var token: String? = null
    var loginNome: String? = null

    fun setDadosPessoais(nome: String, sobrenome: String, email: String, cpf: String) {
        this.nome = nome
        this.sobrenome = sobrenome
        this.email = email
        this.cpf = cpf
        Log.d("TatuadorViewModel", "Dados Pessoais: $nome, $sobrenome, $email, $cpf")
    }

    fun setDadosContato(celular: String, senha: String) {
            this.celular = celular
            this.senha = senha
    }

    fun setDadosFinais(resumo: String, estilos: List<Estilo>) {
        this.resumo = resumo
        this.estilos = estilos
    }


    fun enviarDados(onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        val tatuador = TatuadorCriacao(
            nome = nome,
            sobrenome = sobrenome,
            cpf = cpf,
            celular = celular,
            email = email,
            senha = senha,
            resumo = resumo,
            estilos = estilos
        )
        createTatuador(tatuador, onSuccess, onError)
    }


    private fun createTatuador(tatuador: TatuadorCriacao, onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.tatuadorApi.createTatuador(tatuador)
                if (response.code() == 201) {
                    onSuccess("Cadastro realizado com sucesso!")
                } else {
                    onError("Falha ao cadastrar o tatuador: ${response.message()}")
                }
            } catch (e: Exception) {
                onError("Erro: ${e.localizedMessage}")
            }
        }
    }

    // Função de login
    fun login(onSuccess: (LoginResponse) -> Unit, onError: (String) -> Unit) {
        val loginRequest = LoginRequest(email = loginEmail, senha = loginSenha)

        viewModelScope.launch {
            try {
                val response = RetrofitInstance.tatuadorApi.login(loginRequest)
                if (response.token.isNotEmpty()) {
                    userId = response.userId
                    token = response.token
                    loginNome = response.nome

                    onSuccess(response)
                } else {
                    onError("Falha ao realizar login: ${response.token}")
                }
            } catch (e: Exception) {
                onError("Erro: ${e.localizedMessage}")
            }
        }
    }


}