package com.example.northinkmobileandroid.ui

import android.icu.text.NumberFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextRange
import androidx.navigation.NavController
import com.example.northinkmobileandroid.R
import java.text.DecimalFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GerenciamentoContaPortifolio(navController: NavController) {

    var precoMinimo by remember { mutableStateOf("") }
    var experiencia by remember { mutableStateOf("") }
    var biografia by remember { mutableStateOf("") }

    var biografiaError by remember { mutableStateOf("") }
    var precoMinimoError by remember { mutableStateOf("") }
    var experienciaError by remember { mutableStateOf("") }
    var instagramError by remember { mutableStateOf("") }
    var estilosError by remember { mutableStateOf("") }

    var instagram by remember { mutableStateOf("") }

    val estilos = listOf("Old School", "New School", "Realismo", "Aquarela", "Blackwork", "Minimalismo",
        "Lettering", "Geométrico", "Pontilhismo", "Neo Tradition", "Oriental", "Trash Polka")
    val estilosSelecionados = remember { mutableStateListOf<String>() }

    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    val experienciaOptions = listOf(
        "Menos de 1 ano",
        "1 a 3 anos",
        "3 a 5 anos",
        "5 a 10 anos",
        "Mais de 10 anos"
    )

    fun formatPrecoMinimo(input: String): String {
        return try {
            val cleanString = input.replace("[R$,]".toRegex(), "")
            val parsed = cleanString.toDoubleOrNull() ?: 0.0
            val formatter = java.text.NumberFormat.getCurrencyInstance(Locale("pt", "BR")) as DecimalFormat
            formatter.applyPattern("R$ ###,##0.00")
            formatter.format(parsed / 100)
        } catch (e: Exception) {
            "R$ 0,00"
        }
    }

    val scrollState = rememberScrollState()

    // Layout principal com rolagem
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 60.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Grid de Estilos
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.height(200.dp) // Definido um limite de altura para evitar problemas de layout
        ) {
            items(estilos) { estilo ->
                val isSelected = estilosSelecionados.contains(estilo)

                Box(
                    modifier = Modifier
                        .background(
                            color = if (isSelected) Color(0xFF9333EA) else Color(0xFF888C91),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(8.dp)
                        .clickable {
                            if (isSelected) {
                                estilosSelecionados.remove(estilo)
                            } else {
                                estilosSelecionados.add(estilo)
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = estilo, color = Color.White, fontSize = 14.sp)
                }
            }
        }

        // Preço Mínimo input
        OutlinedTextField(
            value = precoMinimo,
            onValueChange = {
                precoMinimo = formatPrecoMinimo(it)
            },
            label = { Text("Preço Mínimo", color = Color.Black) },
            singleLine = true,
            isError = precoMinimoError.isNotEmpty(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
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
            modifier = Modifier.fillMaxWidth()
        )
        if (precoMinimoError.isNotEmpty()) {
            Text(text = precoMinimoError, color = Color.Red, fontSize = 12.sp)
        }

        // Tempo de experiência dropdown
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = experiencia,
                onValueChange = {},
                label = { Text("Selecione o tempo de Experiência", color = Color.Black) },
                readOnly = true,
                isError = experienciaError.isNotEmpty(),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown Arrow",
                        modifier = Modifier.clickable { expanded = !expanded }
                    )
                },
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
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .clickable { expanded = true }
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                experienciaOptions.forEach { option ->
                    DropdownMenuItem(
                        onClick = {
                            experiencia = option
                            expanded = false
                        },
                        text = {
                            Text(text = option)
                        }
                    )
                }
            }
        }
        if (experienciaError.isNotEmpty()) {
        Text(text = experienciaError, color = Color.Red, fontSize = 12.sp)
    }

        // Biografia input
        OutlinedTextField(
            value = biografia,
            onValueChange = { biografia = it
                if (biografia.length >= 10) {
                    biografiaError = "" // Limpar mensagem de erro se o campo for válido
                } },
            label = { Text("Biografia", color = Color.Black) },
            modifier = Modifier.fillMaxWidth().height(200.dp),
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
            isError = biografiaError.isNotEmpty()
        )
        if (biografiaError.isNotEmpty()) {
            Text(text = biografiaError, color = Color.Red, fontSize = 12.sp)
        }


        // Instagram input
        OutlinedTextField(
            value = instagram,
            onValueChange = { instagram = it },
            label = { Text("Instagram", color = Color.Black) },
            isError = instagramError.isNotEmpty(),
            singleLine = true,
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.instagram), // Substitua pelo ícone correto
                    contentDescription = "Instagram",
                    tint = Color.Unspecified
                )
            },
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
        if (instagramError.isNotEmpty()) {
        Text(text = instagramError, color = Color.Red, fontSize = 12.sp)
    }

        // Botão de Salvar
        Button(
            onClick = {
                // Validação de todos os campos
                var isValid = true
                if (precoMinimo.isEmpty()) {
                    precoMinimoError = "O preço mínimo é obrigatório."
                    isValid = false
                } else {
                    precoMinimoError = ""
                }

                if (experiencia.isEmpty()) {
                    experienciaError = "O tempo de experiência é obrigatório."
                    isValid = false
                } else {
                    experienciaError = ""
                }

                if (biografia.length < 10) {
                    biografiaError = "A biografia precisa ter pelo menos 10 caracteres."
                    isValid = false
                } else {
                    biografiaError = ""
                }

                if (instagram.isEmpty()) {
                    instagramError = "O Instagram é obrigatório."
                    isValid = false
                } else {
                    instagramError = ""
                }

                if (estilosSelecionados.isEmpty()) {
                    estilosError = "Selecione pelo menos um estilo."
                    isValid = false
                } else {
                    estilosError = ""
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
