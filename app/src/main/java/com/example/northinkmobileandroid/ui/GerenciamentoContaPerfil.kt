package com.example.northinkmobileandroid.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.time.format.TextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun GerenciamentoContaPerfil(navController: NavController) {

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
    var errorMessage by remember { mutableStateOf("") }
    var nomeError by remember { mutableStateOf(false) }
    var sobrenomeError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }

    fun celularMask(value: String): String {
        val digits = value.replace(Regex("[^\\d]"), "") // Remove todos os caracteres não numéricos

        return when {
            digits.length <= 2 -> "($digits"
            digits.length <= 7 -> "(${digits.substring(0, 2)}) ${digits.substring(2)}"
            else -> "(${digits.substring(0, 2)}) ${digits.substring(2, 7)}-${digits.substring(7, minOf(digits.length, 11))}"
        }
    }

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
                        celular= TextFieldValue(masked, TextRange(cursorPosition + (masked.length - newValue.text.length)))
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
                    emailError = email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                    celularError = celular.text.isEmpty() || !isValidPhone(celular.text)
                    passwordError = password.isEmpty() || password.length < 6
                    confirmPasswordError = confirmPassword != password

                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9333EA)),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally) // Centraliza horizontalmente
                    .padding(top = 16.dp) // Adiciona uma margem acima
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
        }
    }

