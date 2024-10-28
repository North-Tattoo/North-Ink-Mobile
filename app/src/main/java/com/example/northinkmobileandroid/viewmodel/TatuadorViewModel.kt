    package com.example.northinkmobileandroid.viewmodel

    import android.util.Log
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import com.example.northinkmobileandroid.api.RetrofitInstance
    import com.example.northinkmobileandroid.data.model.Estilo
    import com.example.northinkmobileandroid.data.model.LoginRequest
    import com.example.northinkmobileandroid.data.model.LoginResponse
    import com.example.northinkmobileandroid.data.model.TatuadorAtualizacaoPerfil
    import com.example.northinkmobileandroid.data.model.TatuadorCriacao
    import kotlinx.coroutines.launch
    import retrofit2.Response
    import android.content.Context
    import androidx.lifecycle.LiveData
    import androidx.lifecycle.MutableLiveData
    import com.example.northinkmobileandroid.api.AuthRepository
    import com.example.northinkmobileandroid.api.TatuadorApi
    import com.example.northinkmobileandroid.data.model.EnderecoCriacao
    import com.example.northinkmobileandroid.data.model.Estudio
    import com.example.northinkmobileandroid.data.model.EstudioCriacao
    import com.example.northinkmobileandroid.data.model.TatuadorAtualizacaoPortifolio
    import com.example.northinkmobileandroid.data.model.TatuadorListagemPortifolio
    import com.example.northinkmobileandroid.di.SessaoUsuario
    import org.koin.core.component.KoinComponent
    import org.koin.core.component.inject


    class TatuadorViewModel : ViewModel(), KoinComponent {

        private val authRepository: AuthRepository = AuthRepository()

        private val sessaoUsuario: SessaoUsuario by inject()


        var nome: String = ""
        var sobrenome: String = ""
        var email: String = ""
        var cpf: String = ""
        var celular: String = ""
        var senha: String = ""
        var resumo: String = ""
        var estilos: List<Estilo> = listOf()


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
        // Variáveis para armazenar o login
        var loginEmail: String = ""
        var loginSenha: String = ""
        var token: String? = null
        var loginNome: String? = null

        var userId: Long? = null
            private set

        // Função de login
        fun login(onSuccess: (LoginResponse) -> Unit, onError: (String) -> Unit, context: Context) {
            val loginRequest = LoginRequest(email = loginEmail, senha = loginSenha)

            viewModelScope.launch {
                try {
                    val response = RetrofitInstance.tatuadorApi.login(loginRequest)
                    Log.d("TatuadorViewModel", "LoginResponse: $response")

                    if (response.token.isNotEmpty()) {
                        userId = response.userId
                        token = response.token
                        loginNome = response.nome

                        // Armazena o userId, token e nome no SharedPreferences
                        loginUser(response, context)

                        val sessaoUsuario = SessaoUsuario(
                            userId = response.userId,
                            login = loginEmail,
                            nome = response.nome,
                            token = response.token
                        )

                        authRepository.updateSession(sessaoUsuario)

                        // (Opcional) Salva a sessão no SharedPreferences
                        authRepository.saveSession(sessaoUsuario, context)

                        onSuccess(response)

                    } else {
                        onError("Falha ao realizar login: ${response.token}")
                    }
                } catch (e: Exception) {
                    onError("Erro: ${e.localizedMessage}")
                }
            }
        }

        // Função para armazenar os dados de login no SharedPreferences
        private fun loginUser(loginResponse: LoginResponse, context: Context) {
            userId = loginResponse.userId
            Log.d("TatuadorViewModel", "userId armazenado: $userId")

            val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putLong("userId", loginResponse.userId)
            editor.putString("token", loginResponse.token)
            editor.putString("nome", loginResponse.nome)
            editor.apply()
        }

        fun atualizarPerfilTatuador(usuario: TatuadorAtualizacaoPerfil, context: Context, onSuccess: () -> Unit, onError: (String) -> Unit) {
            viewModelScope.launch {
                try {

                    // Recupera o userId do SharedPreferences
                    val sharedPreferences =
                        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                    val storedUserId = sharedPreferences.getLong("userId", -1L)

                    if (storedUserId != -1L) {
                        userId = storedUserId
                        Log.d("TatuadorViewModel", "userId recuperado: $userId")

                        // Prossegue com a atualização
                        Log.d("TatuadorViewModel", "Enviando dados de atualização: $usuario")
                        val response = RetrofitInstance.tatuadorApi.atualizarUsuario(userId!!, usuario)
                        if (response.isSuccessful) {
                            onSuccess()
                        } else {
                            onError("Erro ao atualizar perfil: ${response.code()} - ${response.message()}")
                        }
                    } else {
                        onError("Erro: userId é nulo ou não foi recuperado corretamente")
                    }
                } catch (e: Exception) {
                    onError("Erro: ${e.message}")
                }
            }
        }

        fun atualizarPortifolioTatuador(
            precoMinimo: Double,
            tempoExperiencia: String,
            biografia: String,
            instagram: String,
            onSuccess: () -> Unit,
            onError: (String) -> Unit,
            context: Context
        ) {
            viewModelScope.launch {
                try {
                    // Recupera o userId do SharedPreferences
                    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                    val storedUserId = sharedPreferences.getLong("userId", -1L)

                    if (storedUserId != -1L) {
                        userId = storedUserId
                        Log.d("TatuadorViewModel", "userId recuperado: $userId")

                        // Envia os dados de atualização de portfólio
                        val portifolioAtualizacao = TatuadorAtualizacaoPortifolio(
                            id = storedUserId,
                            precoMin = precoMinimo,
                            anosExperiencia = tempoExperiencia,
                            resumo = biografia,
                            instagram = instagram
                        )

                        Log.d("TatuadorViewModel", "Enviando dados de atualização de portfólio: $portifolioAtualizacao")
                        val response = RetrofitInstance.tatuadorApi.atualizarPortifolio(userId!!, portifolioAtualizacao)

                        if (response.isSuccessful) {
                            onSuccess()
                        } else {
                            onError("Erro ao atualizar portfólio: ${response.code()} - ${response.message()}")
                        }
                    } else {
                        onError("Erro: userId não foi recuperado corretamente")
                    }
                } catch (e: Exception) {
                    onError("Erro: ${e.message}")
                }
            }
        }

        private val _tatuadorPortfolio = MutableLiveData<TatuadorListagemPortifolio>()
        val tatuadorPortfolio: LiveData<TatuadorListagemPortifolio> = _tatuadorPortfolio

        private val _error = MutableLiveData<String>()
        val error: LiveData<String> = _error


        fun getTatuadorPortfolio(id: Long) {
            Log.d("TatuadorViewModel", "Chamando getTatuadorPortfolio com userId: $id")
            viewModelScope.launch {
                try {
                    val response = RetrofitInstance.tatuadorApi.getTatuadorPortfolio(id)
                    if (response.isSuccessful) {
                        _tatuadorPortfolio.value = response.body()
                    } else {
                        _error.value = "Erro ao carregar dados"
                    }
                } catch (e: Exception) {
                    _error.value = "Erro: ${e.localizedMessage}"
                }
            }
        }

        private var _estudioId: Long? = null

        fun criarEstudio(
            nome: String,
            descricao: String,
            context: Context,
            onSuccess: (Estudio) -> Unit,
            onError: (String) -> Unit
        ) {
            viewModelScope.launch {
                try {
                    // Recupera o userId e o token do SharedPreferences
                    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                    val storedUserId = sharedPreferences.getLong("userId", -1L)
                    val sessaoAtual = authRepository.getSession()
                    val token = sharedPreferences.getString("token", null)

                    if (storedUserId != -1L && token != null) {
                        val estudioCriacao = EstudioCriacao(
                            nome = nome,
                            descricao = descricao,
                            fkUsuario = storedUserId
                        )
                        Log.d("TatuadorViewModel", "Token recuperado: ${sessaoUsuario?.token}")

                        // Faz a requisição para criar o estúdio com o Bearer Token
                        val response = RetrofitInstance.tatuadorApi.criarEstudio("Bearer $token", estudioCriacao)

                        if (response.isSuccessful) {
                            val estudioCriado = response.body()
                            if (estudioCriado != null) {
                                _estudioId = estudioCriado.id
                                onSuccess(estudioCriado)
                            } else {
                                onError("Erro: Resposta vazia ao criar estúdio.")
                            }
                        } else {
                            onError("Erro ao criar estúdio: ${response.code()} - ${response.message()}")
                        }
                    } else {
                        onError("Erro: Não foi possível recuperar o userId ou o token")
                    }
                } catch (e: Exception) {
                    onError("Erro: ${e.localizedMessage}")
                }
            }
        }



        fun criarEndereco(
            rua: String,
            numero: Int,
            complemento: String,
            cep: String,
            bairro: String,
            cidade: String,
            estado: String,
            context: Context,
            onSuccess: () -> Unit,
            onError: (String) -> Unit
        ) {
            viewModelScope.launch {
                try {
                    // Recupera o userId e o token do SharedPreferences
                    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                    val storedUserId = sharedPreferences.getLong("userId", -1L)
                    val sessaoAtual = authRepository.getSession()
                    val token = sharedPreferences.getString("token", null)


                        if (_estudioId != null && storedUserId != -1L && token != null) {
                            val enderecoCriacao = EnderecoCriacao(
                                rua = rua,
                                numero = numero,
                                complemento = complemento,
                                cep = cep,
                                bairro = bairro,
                                cidade = cidade,
                                estado = estado,
                                fkEstudio = _estudioId!!
                            )

                            val response = RetrofitInstance.tatuadorApi.criarEndereco("Bearer $token", enderecoCriacao)

                            if (response.isSuccessful) {
                                onSuccess()
                            } else {
                                onError("Erro ao criar endereço: ${response.code()} - ${response.message()}")
                            }
                        } else {
                        onError("Erro: Não foi possível recuperar o userId ou o token")
                    }
                } catch (e: Exception) {
                    onError("Erro: ${e.localizedMessage}")
                }
            }
        }

    }