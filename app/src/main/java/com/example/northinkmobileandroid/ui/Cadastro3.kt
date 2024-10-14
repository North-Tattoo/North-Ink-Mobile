package com.example.northinkmobileandroid.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.northinkmobileandroid.data.model.Estilo
import com.example.northinkmobileandroid.R
import com.example.northinkmobileandroid.viewmodel.TatuadorViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cadastro3(
    navController: NavHostController,
    tatuadorViewModel: TatuadorViewModel = viewModel()
) {

    // Lista de estilos selecionáveis
    val estilos = listOf("Old School", "New School", "Realismo", "Aquarela", "Blackwork", "Minimalismo"
        , "Lettering", "Geométrico", "Pontilhismo", "Neo Tradition", "Oriental", "Trash Polka")
    val estilosSelecionados = remember { mutableStateListOf<String>() }
    var biografia by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    var biografiaError by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    var showSnackbar by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }


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
                text = "Personalize seu perfil",
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
                .height(500.dp)
                .align(Alignment.Center)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
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
                Spacer(modifier = Modifier.width(20.dp))
                // Input de Biografia
                OutlinedTextField(
                    value = biografia,
                    onValueChange = {
                        biografia = it
                        if (biografia.length >= 10) {
                            biografiaError = "" // Limpar mensagem de erro se o campo for válido
                        }
                                    },
                    label = { Text("Nos conte um pouco sobre sua jornada como tatuador(a).", color = Color.Black) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp), // Campo maior para biografia
                    keyboardOptions = KeyboardOptions.Default,
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
                    shape = RoundedCornerShape(20.dp),
                    isError = biografiaError.isNotEmpty()
                )
                if (biografiaError.isNotEmpty()) {
                    Text(text = biografiaError, color = Color.Red, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.width(20.dp))

                // Botão
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

                    Spacer(modifier = Modifier.width(8.dp)) // Espaçamento entre os botões

                    Button(
                        onClick = {
                            if (biografia.length < 10) {
                                biografiaError = "A biografia precisa ter pelo menos 10 caracteres."
                            } else {
                                tatuadorViewModel.setDadosFinais(
                                    biografia,
                                    estilosSelecionados.map { Estilo(it) }
                                )
                                coroutineScope.launch {
                                    tatuadorViewModel.enviarDados(
                                        onSuccess = { successMessage ->
                                            snackbarMessage = successMessage
                                            showSnackbar = true
                                        },
                                        onError = { errorMessage ->
                                            coroutineScope.launch {
                                                snackbarHostState.showSnackbar("Erro: $errorMessage")
                                            }
                                        }
                                    )
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9333EA)), // Mantém a cor do botão Cadastrar
                        modifier = Modifier
                            .weight(1f)
                            .height(40.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "Cadastrar",
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
        if (showSnackbar) {
            LaunchedEffect(snackbarMessage) {
                snackbarHostState.showSnackbar(snackbarMessage)
                kotlinx.coroutines.delay(500)
                navController.navigate("login")
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

