package com.example.northinkmobileandroid.ui

import android.util.Log
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.northinkmobileandroid.data.model.TatuadorAtualizacaoPerfil
import com.example.northinkmobileandroid.viewmodel.TatuadorViewModel
import java.time.format.TextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun GerenciamentoContaPerfil(
    navController: NavController,
    tatuadorViewModel: TatuadorViewModel
    ) {

    var nome by remember { mutableStateOf("") }
    var sobrenome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPassword by remember { mutableStateOf("") }

    var celularError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var confirmPasswordError by remember { mutableStateOf(false) }
    var nomeError by remember { mutableStateOf(false) }
    var sobrenomeError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }

    // Variáveis para exibir o Snackbar
    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }

    var errorMessage by remember { mutableStateOf("") }
    var successMessage by remember { mutableStateOf("") }

    fun celularMask(value: String): String {
        val digits = value.replace(Regex("[^\\d]"), "") // Remove todos os caracteres não numéricos

        return when {
            digits.length <= 2 -> "($digits"
            digits.length <= 7 -> "(${digits.substring(0, 2)}) ${digits.substring(2)}"
            else -> "(${digits.substring(0, 2)}) ${digits.substring(2, 7)}-${
                digits.substring(
                    7,
                    minOf(digits.length, 11)
                )
            }"
        }
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {


        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome", color = Color.Black) },
            isError = nomeError,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFFA855F7),
                unfocusedBorderColor = Color(0xFFA855F7),
                focusedLabelColor = Color(0xFFA855F7),
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                unfocusedLabelColor = Color.Gray,
                errorTextColor = Color.Black,
                containerColor = Color.White,

                ),
            shape = RoundedCornerShape(20.dp)
        )
        if (nomeError) {
            Text("Nome deve ter pelo menos 3 caracteres.", color = Color.Red, fontSize = 12.sp)
        }

        // Input de Sobrenome
        OutlinedTextField(
            value = sobrenome,
            onValueChange = { sobrenome = it },
            label = { Text("Sobrenome", color = Color.Black) },
            isError = sobrenomeError,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFFA855F7),
                unfocusedBorderColor = Color(0xFFA855F7),
                containerColor = Color.White,
                focusedTextColor = Color.Black,
                errorTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            shape = RoundedCornerShape(20.dp)
        )
        if (sobrenomeError) {
            Text("Sobrenome deve ter pelo menos 3 caracteres.", color = Color.Red, fontSize = 12.sp)
        }

        // Input de Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email", color = Color.Black) },
            isError = emailError,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFFA855F7),
                unfocusedBorderColor = Color(0xFFA855F7),
                containerColor = Color.White,
                focusedTextColor = Color.Black,
                errorTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            shape = RoundedCornerShape(20.dp)
        )
        if (emailError) {
            Text("Por favor, insira um email válido.", color = Color.Red, fontSize = 12.sp)
        }
        OutlinedTextField(
            value = celular,
            onValueChange = { newValue ->

                val digits = newValue.text.replace(Regex("[^\\d]"), "")

                // Limita a 11 dígitos
                if (digits.length <= 11) {
                    val masked = celularMask(digits)
                    val cursorPosition = newValue.selection.start
                    celular = TextFieldValue(
                        masked,
                        TextRange(cursorPosition + (masked.length - newValue.text.length))
                    )
                }
            },
            label = { Text("Celular", color = Color.Black) },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(),
            isError = celularError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFFA855F7),
                unfocusedBorderColor = Color(0xFFA855F7),
                focusedLabelColor = Color(0xFFA855F7),
                unfocusedLabelColor = Color.Gray,
                containerColor = Color.White,
                focusedTextColor = Color.Black,
                errorTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            shape = RoundedCornerShape(20.dp)
        )
        if (celularError) {
            Text("Celular inválido", color = Color.Red, fontSize = 12.sp)
        }
        // Input de Senha
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha", color = Color.Black) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            isError = passwordError,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFFA855F7),
                unfocusedBorderColor = Color(0xFFA855F7),
                focusedLabelColor = Color(0xFFA855F7),
                unfocusedLabelColor = Color.Gray,
                containerColor = Color.White,
                focusedTextColor = Color.Black,
                errorTextColor = Color.Black,
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
        if (passwordError) {
            Text("Senha deve ter no mínimo 6 caracteres", color = Color.Red, fontSize = 12.sp)
        }

        // Input de Confirmação de Senha
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirmação de Senha", color = Color.Black) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            isError = confirmPasswordError,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFFA855F7),
                unfocusedBorderColor = Color(0xFFA855F7),
                focusedLabelColor = Color(0xFFA855F7),
                unfocusedLabelColor = Color.Gray,
                containerColor = Color.White,
                focusedTextColor = Color.Black,
                errorTextColor = Color.Black,
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
        if (confirmPasswordError) {
            Text("As senhas não coincidem", color = Color.Red, fontSize = 12.sp)
        }

        // Botão Salvar
        Button(
            onClick = {
                nomeError = nome.length < 3
                sobrenomeError = sobrenome.length < 3
                emailError =
                    email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                celularError = celular.text.isEmpty() || !isValidPhone(celular.text)
                passwordError = password.isEmpty() || password.length < 6
                confirmPasswordError = confirmPassword != password

                if (!nomeError && !sobrenomeError && !emailError && !celularError && !passwordError && !confirmPasswordError) {
                    val usuarioAtualizado = TatuadorAtualizacaoPerfil(
                        nome = nome,
                        sobrenome = sobrenome,
                        email = email,
                        senha = password,
                        novaSenha = confirmPassword,
                        celular = celular.text
                    )

                    // Passar o contexto para a função de atualização
                    tatuadorViewModel.atualizarPerfilTatuador(
                        usuarioAtualizado,
                        context,  // Contexto obtido aqui
                        onSuccess = {
                            snackbarMessage = "Perfil Atualizado com sucesso!"
                            showSnackbar = true
                            Log.d("UpdateSuccess", "Perfil atualizado com sucesso!")
                        },
                        onError = { error ->
                            errorMessage = "Erro ao atualizar perfil: $error"
                            Log.d("UpdateError", "Erro ao atualizar perfil: $error")
                        }
                    )
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9333EA)),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
                .width(200.dp)
                .height(40.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "Salvar",
                color = Color.White,
                fontSize = 18.sp
            )
        }

        if (showSnackbar) {
            LaunchedEffect(snackbarMessage) {
                snackbarHostState.showSnackbar(snackbarMessage)
                kotlinx.coroutines.delay(500)
            }
        }

        // Exibir o Snackbar no topo
        if (showSnackbar) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
            ) {
                SnackbarHost(
                    hostState = snackbarHostState,
                    modifier = Modifier
                        .align(Alignment.TopCenter),
                    snackbar = { snackbarData ->
                        androidx.compose.material3.Snackbar(
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
                                    snackbarData.visuals.actionLabel?.let { actionLabel ->
                                        Text(
                                            text = actionLabel,
                                            color = Color.Yellow,
                                            modifier = Modifier.clickable {
                                                snackbarData.performAction()
                                            }
                                        )
                                    }
                                }
                            }
                        )
                    }
                )
            }
        }
    }
}

