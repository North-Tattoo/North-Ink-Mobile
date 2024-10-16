package com.example.northinkmobileandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.northinkmobileandroid.viewmodel.TatuadorViewModel
import com.example.northinkmobileandroid.ui.Cadastro
import com.example.northinkmobileandroid.ui.Cadastro2
import com.example.northinkmobileandroid.ui.Cadastro3
import com.example.northinkmobileandroid.ui.GerenciamentoConta
import com.example.northinkmobileandroid.ui.GerenciamentoContaEdicao
import com.example.northinkmobileandroid.ui.GerenciamentoContaEstudio
import com.example.northinkmobileandroid.ui.GerenciamentoContaPerfil
import com.example.northinkmobileandroid.ui.GerenciamentoContaPortifolio
import com.example.northinkmobileandroid.ui.Home
import com.example.northinkmobileandroid.ui.ListagemTatuador
import com.example.northinkmobileandroid.ui.Login
import com.example.northinkmobileandroid.ui.PerfilTatuador
import com.example.northinkmobileandroid.ui.theme.NorthInkMobileAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NorthInkMobileAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BarraInferiorNavegacao(
                        rememberNavController(),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BarraInferiorNavegacao(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val selectedIcon = remember { mutableStateOf("home") }
    val iconColor = Color(0xFF581C87)
    val cardColor = Color(0xFFFAF5FF)
    val purpleBarColor = Color(0xFF9333EA)

    val tatuadorViewModel: TatuadorViewModel = viewModel()

    Box(modifier = modifier
        .fillMaxSize()
        .background(Color.Transparent)) {
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.fillMaxSize()
        ) {
            composable("home") {
                Home(navController = navController)
            }
            composable("listagem") {
                ListagemTatuador(navController)
            }
            composable("login") {
                Login(navController = navController)
            }
            composable("cadastro") {    
                Cadastro(navController = navController, tatuadorViewModel)
            }
            composable("cadastro2") {
                Cadastro2(navController = navController, tatuadorViewModel)
            }
            composable("cadastro3") {
                Cadastro3(navController = navController, tatuadorViewModel)
            }
            composable("perfilTatuador") {
                PerfilTatuador(navController = navController)
            }
            composable("gerenciamentoConta") {
                GerenciamentoConta(navController = navController)
            }
            composable("gerenciamentoContaEdicao") {
                GerenciamentoContaEdicao(navController = navController)
            }
            composable("gerenciamentoContaPerfil") {
                GerenciamentoContaPerfil(navController = navController)
            }
            composable("gerenciamentoContaPortifolio") {
                GerenciamentoContaPortifolio(navController = navController)
            }
            composable("gerenciamentoContaEstudio") {
                GerenciamentoContaEstudio(navController = navController)
            }

        }
        Card(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp),
            colors = CardDefaults.cardColors(containerColor = cardColor)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(46.dp)
            ) {
                // Card para Home
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RectangleShape,
                    colors = CardDefaults.cardColors(containerColor = cardColor)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(
                            onClick = {
                                navController.navigate("home")
                                selectedIcon.value = "home"
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Home,
                                contentDescription = "Home",
                                tint = iconColor,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                }

                // Card para Listagem
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RectangleShape,
                    colors = CardDefaults.cardColors(containerColor = cardColor)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(onClick = { navController.navigate("listagem") }) {
                            Icon(
                                imageVector = Icons.Filled.Search,
                                contentDescription = "Listagem",
                                tint = iconColor,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                }

                // Card para Perfil
                Card(
                    modifier = Modifier.weight(1f),
                    shape = RectangleShape,
                    colors = CardDefaults.cardColors(containerColor = cardColor)
                )
                {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(onClick = { navController.navigate("login") }) {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = "Login",
                                tint = iconColor,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(
    device = "spec:width=411dp,height=891dp", showSystemUi = true, showBackground = true
)
@Composable
fun GreetingPreview() {
    NorthInkMobileAndroidTheme {
        BarraInferiorNavegacao(rememberNavController())
    }
}