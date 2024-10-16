package com.example.northinkmobileandroid.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GerenciamentoContaEstudio(navController: NavController) {

    var nomeEstudio by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var cep by remember { mutableStateOf("") }
    var rua by remember { mutableStateOf("") }
    var complemento by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("") }
    var bairro by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }
    var cidade by remember { mutableStateOf("") }

    // Erros
    var nomeEstudioError by remember { mutableStateOf("") }
    var descricaoError by remember { mutableStateOf("") }
    var cepError by remember { mutableStateOf("") }
    var ruaError by remember { mutableStateOf("") }
    var complementoError by remember { mutableStateOf("") }
    var estadoError by remember { mutableStateOf("") }
    var bairroError by remember { mutableStateOf("") }
    var numeroError by remember { mutableStateOf("") }
    var cidadeError by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 60.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Nome do Estúdio
        OutlinedTextField(
            value = nomeEstudio,
            onValueChange = { nomeEstudio = it },
            label = { Text("Nome do Estúdio", color = Color.Black) },
            isError = nomeEstudioError.isNotEmpty(),
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
        if (nomeEstudioError.isNotEmpty()) {
            Text(text = nomeEstudioError, color = Color.Red, fontSize = 12.sp)
        }

        // Descrição
        OutlinedTextField(
            value = descricao,
            onValueChange = { descricao = it },
            label = { Text("Descrição", color = Color.Black) },
            modifier = Modifier.fillMaxWidth().height(100.dp),
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
            shape = RoundedCornerShape(20.dp),
            isError = descricaoError.isNotEmpty()
        )
        if (descricaoError.isNotEmpty()) {
            Text(text = descricaoError, color = Color.Red, fontSize = 12.sp)
        }

        // CEP
        OutlinedTextField(
            value = cep,
            onValueChange = { cep = it },
            label = { Text("CEP", color = Color.Black) },
            isError = cepError.isNotEmpty(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
        if (cepError.isNotEmpty()) {
            Text(text = cepError, color = Color.Red, fontSize = 12.sp)
        }

        // Rua
        OutlinedTextField(
            value = rua,
            onValueChange = { rua = it },
            label = { Text("Rua", color = Color.Black) },
            isError = ruaError.isNotEmpty(),
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
        if (ruaError.isNotEmpty()) {
            Text(text = ruaError, color = Color.Red, fontSize = 12.sp)
        }

        // Complemento
        OutlinedTextField(
            value = complemento,
            onValueChange = { complemento = it },
            label = { Text("Complemento", color = Color.Black) },
            isError = complementoError.isNotEmpty(),
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
        if (complementoError.isNotEmpty()) {
            Text(text = complementoError, color = Color.Red, fontSize = 12.sp)
        }

        // Estado
        OutlinedTextField(
            value = estado,
            onValueChange = { estado = it },
            label = { Text("Estado", color = Color.Black) },
            isError = estadoError.isNotEmpty(),
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
        if (estadoError.isNotEmpty()) {
            Text(text = estadoError, color = Color.Red, fontSize = 12.sp)
        }

        // Bairro
        OutlinedTextField(
            value = bairro,
            onValueChange = { bairro = it },
            label = { Text("Bairro", color = Color.Black) },
            isError = bairroError.isNotEmpty(),
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
        if (bairroError.isNotEmpty()) {
            Text(text = bairroError, color = Color.Red, fontSize = 12.sp)
        }

        // Número
        OutlinedTextField(
            value = numero,
            onValueChange = { numero = it },
            label = { Text("Número", color = Color.Black) },
            isError = numeroError.isNotEmpty(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
        if (numeroError.isNotEmpty()) {
            Text(text = numeroError, color = Color.Red, fontSize = 12.sp)
        }
        // Cidade
        OutlinedTextField(
            value = cidade,
            onValueChange = { cidade = it },
            label = { Text("Cidade", color = Color.Black) },
            isError = cidadeError.isNotEmpty(),
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
        if (cidadeError.isNotEmpty()) {
            Text(text = cidadeError, color = Color.Red, fontSize = 12.sp)
        }

        // Botão de salvar
        Button(
            onClick = {
                // Validação de todos os campos
                var isValid = true
                if (nomeEstudio.isEmpty()) {
                    nomeEstudioError = "O nome do estudio é obrigatório."
                    isValid = false
                } else {
                    nomeEstudioError = ""
                }

                if (descricao.isEmpty()) {
                    descricaoError = "O descrição do estudio é obrigatório."
                    isValid = false
                } else {
                    descricaoError = ""
                }

                if (cep.isEmpty()) {
                    cepError = "O CEP é obrigatório."
                    isValid = false
                } else {
                    cepError = ""
                }

                if (rua.isEmpty()) {
                    ruaError = "A rua é obrigatória."
                    isValid = false
                } else {
                    ruaError = ""
                }

                if (complemento.isEmpty()) {
                    complementoError = "O complemento é obrigatório."
                    isValid = false
                } else {
                    complementoError = ""
                }

                if (estado.isEmpty()) {
                    estadoError = "O estado é obrigatório."
                    isValid = false
                } else {
                    estadoError = ""
                }

                if (bairro.isEmpty()) {
                    bairroError = "O bairro é obrigatório."
                    isValid = false
                }  else {
                    bairroError = ""
                }

                if (numero.isEmpty()) {
                    numeroError = "O número é obrigatório."
                    isValid = false
                }  else {
                    numeroError = ""
                }

                if (cidade.isEmpty()) {
                    cidadeError = "A cidade é obrigatória."
                    isValid = false
                } else {
                    cidadeError = ""
                }

                if (isValid) {

                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9333EA)),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(200.dp)
                .height(40.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = "Salvar", color = Color.White, fontSize = 18.sp)
        }
    }
}
