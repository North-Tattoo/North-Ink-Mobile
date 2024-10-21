package com.example.northinkmobileandroid.ui

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Portrait
import androidx.compose.material.icons.filled.Store
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.northinkmobileandroid.R
import com.example.northinkmobileandroid.viewmodel.TatuadorViewModel


@Composable
fun GerenciamentoContaEdicao(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    var selectedTab by remember { mutableStateOf("Perfil") }
    val iconColor = Color(0xFF581C87)
    val context = LocalContext.current

    val tatuadorViewModel: TatuadorViewModel = viewModel()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Cabeçalho com altura reduzida
        Column(
            modifier = Modifier
                .height(170.dp)
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF9333EA),
                            Color(0xFFFEC0FF)
                        )
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Voltar",
                        tint = Color.Black
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 50.dp, end = 20.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.grid_home3),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)

                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Gerencie Sua Conta",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Ajuste as informações da sua conta e estúdio",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Ícone de Perfil
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(100.dp)
                        .clickable { selectedTab = "Perfil" }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Perfil",
                            tint = if (selectedTab == "Perfil") iconColor else Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Perfil",
                            fontSize = 14.sp,
                            color = if (selectedTab == "Perfil") iconColor else Color.Gray
                        )
                    }

                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(2.dp)
                            .background(if (selectedTab == "Perfil") iconColor else Color.Transparent)
                    )
                }

                // Ícone de Portfólio
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(100.dp)
                        .clickable { selectedTab = "Portfólio" }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Portrait,
                            contentDescription = "Portfólio",
                            tint = if (selectedTab == "Portfólio") iconColor else Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Portfólio",
                            fontSize = 14.sp,
                            color = if (selectedTab == "Portfólio") iconColor else Color.Gray
                        )
                    }

                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(2.dp)
                            .background(if (selectedTab == "Portfólio") iconColor else Color.Transparent)
                    )
                }

                // Ícone de Estúdio
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(100.dp)
                        .clickable { selectedTab = "Estúdio" }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Store,
                            contentDescription = "Estúdio",
                            tint = if (selectedTab == "Estúdio") iconColor else Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Estúdio",
                            fontSize = 14.sp,
                            color = if (selectedTab == "Estúdio") iconColor else Color.Gray
                        )
                    }

                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(2.dp)
                            .background(if (selectedTab == "Estúdio") iconColor else Color.Transparent)
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            when (selectedTab) {
                "Perfil" -> {
                    GerenciamentoContaPerfil(navController, tatuadorViewModel)
                }

                "Portfólio" -> {
                    GerenciamentoContaPortifolio(navController, tatuadorViewModel)
                }

                "Estúdio" -> {
                    GerenciamentoContaEstudio(navController)
                }

                else -> {}
            }
        }
    }
}






