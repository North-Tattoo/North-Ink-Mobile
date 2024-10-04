package com.example.northinkmobileandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
    val iconColor = Color(0xFF581C87) // Cor dos ícones
    val cardColor = Color(0xFFFAF5FF) // Cor de fundo dos cartões
    val purpleBarColor = Color(0xFF9333EA)

    Box(modifier = modifier.fillMaxSize().background(Color.Transparent)) {
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.fillMaxSize()
        ) {
            composable("home") {
                Home(navController = navController)
            }
            composable("listagem") {
                ListagemTatuador()
            }
            composable("perfil") {
                PerfilArtista()
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
                        contentAlignment = Alignment.Center // Centraliza o conteúdo
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
                        IconButton(onClick = { navController.navigate("perfil") }) {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = "Perfil",
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NorthInkMobileAndroidTheme {
        BarraInferiorNavegacao(rememberNavController())
    }
}