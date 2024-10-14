package com.example.northinkmobileandroid.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.northinkmobileandroid.R
import com.example.northinkmobileandroid.viewmodel.TatuadorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(
    navController: NavHostController,
    tatuadorViewModel: TatuadorViewModel = viewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var loginErrorMessage by remember { mutableStateOf("") }

    // Variáveis para exibir o Snackbar
    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

     fun validateFields(): Boolean {
        return when {
            email.isEmpty() -> {
                loginErrorMessage = "Por favor, insira o e-mail."
                false
            }
            password.isEmpty() -> {
                loginErrorMessage = "Por favor, insira a senha."
                false
            }
            else -> {
                loginErrorMessage = ""
                true
            }
        }
    }

    fun handleLogin() {
        if (validateFields()) {
            tatuadorViewModel.loginEmail = email
            tatuadorViewModel.loginSenha = password

            tatuadorViewModel.login(
                onSuccess = { loginResponse ->
                    snackbarMessage = "Login realizado com sucesso!"
                    showSnackbar = true
                    Log.d("Login", "Login bem-sucedido: ${loginResponse.token}")
                },
                onError = { error ->
                    loginErrorMessage = "E-mail ou senha inválidos."
                }
            )
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF96989B)),
        contentAlignment = Alignment.TopCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .background(
                    color = Color(0xFFA855F7),
                    shape = RoundedCornerShape(
                        bottomStart = 30.dp,
                        bottomEnd = 30.dp
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logobranca),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.TopCenter)
                    .padding(top = 16.dp)

            )
            Text(
                text = stringResource(id = R.string.boas_vindas_login),
                fontWeight = FontWeight.Medium,
                fontSize = 25.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(30.dp)
            )
        }

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(400.dp)
                .padding(16.dp)
                .align(Alignment.Center)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Input de e-mail
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email", color = Color.Black) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp, bottom = 16.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFFA855F7),
                        unfocusedBorderColor = Color(0xFFA855F7),
                        focusedLabelColor = Color(0xFFA855F7),
                        unfocusedLabelColor = Color.Gray,
                        containerColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                ),
                    shape = RoundedCornerShape(20.dp)
                )

                // Input de senha
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Senha", color = Color.Black) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFFA855F7),
                        unfocusedBorderColor = Color(0xFFA855F7),
                        focusedLabelColor = Color(0xFFA855F7),
                        unfocusedLabelColor = Color.Gray,
                        containerColor = Color.White,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    ),
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (passwordVisible)
                            Icons.Default.Visibility
                        else Icons.Default.VisibilityOff

                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = image, contentDescription = null)
                        }
                    },
                    shape = RoundedCornerShape(20.dp)
                )
                // Exibir mensagem de erro se houver
                if (loginErrorMessage.isNotEmpty()) {
                    Text(
                        text = loginErrorMessage,
                        color = Color.Red,
                        fontSize = 14.sp
                    )
                }

                // Botão de login
                Button(
                    onClick = { handleLogin() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9333EA)),
                    modifier = Modifier
                        .width(200.dp)
                        .height(40.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Entrar",
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
                // Texto abaixo do botão
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Não tem conta? ",
                        color = Color(0xFF8F97A1),
                        fontSize = 14.sp
                    )
                    Text(
                        text = "Faça seu cadastro",
                        color = Color(0xFFA855F7),
                        fontSize = 14.sp,
                        modifier = Modifier.clickable {
                            navController.navigate("cadastro")
                        }
                    )
                }
            }
        }
        // Exibir Snackbar se necessário
        if (showSnackbar) {
            LaunchedEffect(snackbarMessage) {
                snackbarHostState.showSnackbar(snackbarMessage)
                kotlinx.coroutines.delay(500)
                navController.navigate("home")
            }
        }

        if (showSnackbar) {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 16.dp),
                snackbar = { snackbarData ->
                    Snackbar(
                        modifier = Modifier
                            .padding(16.dp)
                            .background(Color(0xFF171717), RoundedCornerShape(12.dp)),
                        containerColor = Color(0xFF171717),
                        contentColor = Color.White,
                        shape = RoundedCornerShape(12.dp),
                        content = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = "Info",
                                    tint = Color(0xFFA855F7),
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = snackbarData.visuals.message,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }
                    )
                }
            )
        }
    }
}
