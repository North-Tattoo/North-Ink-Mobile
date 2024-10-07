package com.example.northinkmobileandroid

import android.annotation.SuppressLint
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cadastro(navController: NavHostController) {

    var nome by remember { mutableStateOf("") }
    var sobrenome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf(TextFieldValue("")) }
    var celular by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPassword by remember { mutableStateOf("") }
    var isFormValid by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    // Função para aplicar máscara ao CPF
    fun cpfMask(value: String): String {
        val digitsOnly = value.replace(Regex("[^\\d]"), "")
        return when (digitsOnly.length) {
            in 0..3 -> digitsOnly
            in 4..5 -> "${digitsOnly.take(3)}.${digitsOnly.drop(3)}"
            in 6..8 -> "${digitsOnly.take(3)}.${digitsOnly.substring(3, 6)}.${digitsOnly.drop(6)}"
            in 9..10 -> "${digitsOnly.take(3)}.${digitsOnly.substring(3, 6)}.${digitsOnly.substring(6, 9)}-${digitsOnly.drop(9)}"
            11 -> "${digitsOnly.take(3)}.${digitsOnly.substring(3, 6)}.${digitsOnly.substring(6, 9)}-${digitsOnly.drop(9)}"
            else -> digitsOnly.take(11)
        }
    }

    fun celularMask(value: String): String {
        val digits = value.replace(Regex("[^\\d]"), "") // Remove todos os caracteres não numéricos

        return when {
            digits.length <= 2 -> "($digits" // Exibe DDD
            digits.length <= 7 -> "(${digits.substring(0, 2)}) ${digits.substring(2)}" // Exibe DDD e 1º bloco do número
            else -> "(${digits.substring(0, 2)}) ${digits.substring(2, 7)}-${digits.substring(7, minOf(digits.length, 11))}" // Exibe DDD, 1º bloco e 2º bloco do número
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
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.logobranca),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.TopCenter)
                    .padding(bottom = 6.dp)
            )
            Text(
                text = stringResource(id = R.string.boas_vindas_cadastro),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
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
                .padding(16.dp)
                .align(Alignment.Center)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Input de Nome
                OutlinedTextField(
                    value = nome,
                    onValueChange = { nome = it },
                    label = { Text("Nome", color = Color.Black) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
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

                // Input de Sobrenome
                OutlinedTextField(
                    value = sobrenome,
                    onValueChange = { sobrenome = it },
                    label = { Text("Sobrenome", color = Color.Black) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
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

                // Input de Email
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email", color = Color.Black) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
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

                // Input de CPF
                OutlinedTextField(
                    value = cpf,
                    onValueChange = {
                        // Obtenha a posição do cursor antes de aplicar a máscara
                        val cursorPosition = it.selection.start

                        // Aplica a máscara ao valor de CPF
                        val masked = cpfMask(it.text)

                        // Define o novo valor do estado, garantindo que a posição do cursor seja atualizada corretamente
                        cpf = TextFieldValue(masked, TextRange(cursorPosition + (masked.length - it.text.length)))
                    },
                    label = { Text("CPF", color = Color.Black) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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

                OutlinedTextField(
                    value = celular,
                    onValueChange = { newValue ->
                        // Remove todos os caracteres não numéricos
                        val digits = newValue.text.replace(Regex("[^\\d]"), "")

                        // Limita a 11 dígitos
                        if (digits.length <= 11) {
                            val masked = celularMask(digits)
                            // Define o novo valor com a máscara e ajusta o cursor
                            val cursorPosition = newValue.selection.start
                            celular= TextFieldValue(masked, TextRange(cursorPosition + (masked.length - newValue.text.length)))
                        }
                    },
                    label = { Text("Celular", color = Color.Black) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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

                // Input de Senha
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Senha", color = Color.Black) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
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

                // Input de Confirmação de Senha
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirmação de Senha", color = Color.Black) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
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

                // Botão de Próximo
                Button(
                    onClick = {
                        // Validação do formulário
                        isFormValid = validateForm(nome, sobrenome, email, cpf.text, celular.text, password, confirmPassword)
                        if (isFormValid) {
                            navController.navigate("cadastro2")
                        } else {
                            errorMessage = "Por favor, preencha todos os campos corretamente."
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9333EA)),
                    modifier = Modifier
                        .width(200.dp)
                        .height(40.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Próximo",
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }

                // Mensagem de erro
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

// Função para validar o formulário
fun validateForm(
    nome: String,
    sobrenome: String,
    email: String,
    cpf: String,
    celular: String,
    password: String,
    confirmPassword: String
): Boolean {
    return nome.isNotEmpty() &&
            sobrenome.isNotEmpty() &&
            email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
            cpf.isNotEmpty() && isValidCPF(cpf) &&
            celular.isNotEmpty() && isValidPhone(celular) &&
            password.isNotEmpty() && password.length >= 6 && // Exemplo: mínimo de 6 caracteres
            password == confirmPassword
}

fun isValidCPF(cpf: String): Boolean {
    // Remover caracteres não numéricos
    val digitsOnly = cpf.replace(Regex("[^\\d]"), "")
    return digitsOnly.length == 11
}


fun isValidPhone(phone: String): Boolean {
    // Remover caracteres não numéricos
    val digitsOnly = phone.replace(Regex("[^\\d]"), "")
    return digitsOnly.length in 10..11
}

