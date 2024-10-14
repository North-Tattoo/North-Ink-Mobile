package com.example.northinkmobileandroid.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import retrofit2.Response
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.northinkmobileandroid.R
import com.example.northinkmobileandroid.data.model.TatuadorCriacao
import com.example.northinkmobileandroid.viewmodel.TatuadorViewModel


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cadastro(
    navController: NavHostController,
    tatuadorViewModel: TatuadorViewModel = viewModel()
) {

    var nome by remember { mutableStateOf("") }
    var sobrenome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf(TextFieldValue("")) }

    var nomeError by remember { mutableStateOf(false) }
    var sobrenomeError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var cpfError by remember { mutableStateOf(false) }

    var errorMessage by remember { mutableStateOf("") }

    // Função para aplicar máscara ao CPF
    fun cpfMask(value: String): String {
        val digitsOnly = value.replace(Regex("[^\\d]"), "")
        return when (digitsOnly.length) {
            in 0..3 -> digitsOnly
            in 4..6 -> "${digitsOnly.take(3)}.${digitsOnly.drop(3)}"
            in 7..9 -> "${digitsOnly.take(3)}.${digitsOnly.substring(3, 6)}.${digitsOnly.drop(6)}"
            in 10..11 -> "${digitsOnly.take(3)}.${digitsOnly.substring(3, 6)}.${digitsOnly.substring(6, 9)}-${digitsOnly.drop(9)}"
            else -> digitsOnly.take(11)
        }
    }

    fun onSuccess(response: Response<TatuadorCriacao>) {
        navController.navigate("cadastro2")  // Ir para a próxima etapa se o cadastro for bem-sucedido
    }

    fun onError(message: String) {
        errorMessage = message  // Exibe a mensagem de erro
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
                text = stringResource(id = R.string.boas_vindas_cadastro),
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
                // Input de Nome
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

                // Input de CPF
                OutlinedTextField(
                    value = cpf,
                    onValueChange = { newValue ->

                          val digitsOnly = newValue.text.replace(Regex("[^\\d]"), "")

                        val masked = cpfMask(digitsOnly)

                        val cursorPosition = newValue.selection.start + (masked.length - newValue.text.length)

                        cpf = TextFieldValue(masked, TextRange(cursorPosition.coerceIn(0, masked.length)))
                    },
                    label = { Text("CPF", color = Color.Black) },
                    isError = cpfError,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = if (cpfError) Color.Red else Color(0xFFA855F7),
                        unfocusedBorderColor = if (cpfError) Color.Red else Color(0xFFA855F7),
                        containerColor = Color.White,
                        focusedTextColor = Color.Black,
                        errorTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    ),
                    shape = RoundedCornerShape(20.dp)
                )
                if (cpfError) {
                    Text("CPF inválido.", color = Color.Red, fontSize = 12.sp)
                }
                // Botão de Próximo
                Button(
                    onClick = {
                        nomeError = nome.length < 3
                        sobrenomeError = sobrenome.length < 3
                        emailError = email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                        cpfError = cpf.text.isEmpty() || !isValidCPF(cpf.text)

                        if (!nomeError && !sobrenomeError && !emailError && !cpfError) {
                            tatuadorViewModel.setDadosPessoais(nome, sobrenome, email, cpf.text)
                            navController.navigate("cadastro2")
                        } else {
                            errorMessage = "Por favor, corrija os erros acima."
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
                        fontSize = 12.sp,
                        color = Color.Red
                    )
                }
            }
        }
    }
}

fun isValidCPF(cpf: String): Boolean {
    // Remover caracteres não numéricos
    val digitsOnly = cpf.replace(Regex("[^\\d]"), "")
    return digitsOnly.length == 11
}



