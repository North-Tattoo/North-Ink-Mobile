package com.example.northinkmobileandroid.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.northinkmobileandroid.R
import com.example.northinkmobileandroid.viewmodel.TatuadorViewModel

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cadastro2(
    navController: NavHostController,
    tatuadorViewModel: TatuadorViewModel = viewModel()
) {

    var celular by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPassword by remember { mutableStateOf("") }

    var celularError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var confirmPasswordError by remember { mutableStateOf(false) }

    var errorMessage by remember { mutableStateOf("") }

    fun celularMask(value: String): String {
        val digits = value.replace(Regex("[^\\d]"), "") // Remove todos os caracteres não numéricos

        return when {
            digits.length <= 2 -> "($digits"
            digits.length <= 7 -> "(${digits.substring(0, 2)}) ${digits.substring(2)}"
            else -> "(${digits.substring(0, 2)}) ${digits.substring(2, 7)}-${digits.substring(7, minOf(digits.length, 11))}"
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
                    .padding(bottom = 35.dp)
                    .align(Alignment.TopCenter)


            )
            Text(
                text = stringResource(id = R.string.boas_vindas_cadastro_2),
                fontWeight = FontWeight.Medium,
                fontSize = 25.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(bottom = 150.dp)
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
//                Input de Celular
                OutlinedTextField(
                    value = celular,
                    onValueChange = { newValue ->
                        // Remove todos os caracteres não numéricos
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
                Row(
                    modifier = Modifier.fillMaxWidth(), // Para que a Row use toda a largura disponível
                    horizontalArrangement = Arrangement.SpaceBetween // Alinha os botões
                ) {
                    Button(
                        onClick = {
                            navController.popBackStack()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF888C91)), // Cor do botão Voltar
                        modifier = Modifier
                            .weight(1f) // Ocupa espaço igual
                            .height(40.dp), // Altura constante
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "Voltar",
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    // Botão de Próximo
                    Button(
                        onClick = {
                            celularError = celular.text.isEmpty() || !isValidPhone(celular.text)
                            passwordError = password.isEmpty() || password.length < 6
                            confirmPasswordError = confirmPassword != password

                            if (!celularError && !passwordError && !confirmPasswordError) {
                                tatuadorViewModel.setDadosContato(celular.text, password)
                                navController.navigate("cadastro3") // Navega para a próxima tela
                            } else {
                                errorMessage = "Por favor, preencha todos os campos corretamente."
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9333EA)),
                        modifier = Modifier
                            .weight(1f) // Ocupa espaço igual
                            .height(40.dp), // Altura constante
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "Próximo",
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }
                }

                // Mensagem de erro
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        fontSize = 12.sp,
                        color = Color.Red
                    )
                }
            }
        }
    }
}

fun isValidPhone(phone: String): Boolean {
    val digitsOnly = phone.replace(Regex("[^\\d]"), "")
    return digitsOnly.length in 10..11
}

