package com.example.northinkmobileandroid

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource

@Composable
fun PerfilArtista(modifier: Modifier = Modifier) {
    val contexto = LocalContext.current

    var selectedTab by remember { mutableStateOf("Tattos") }

    val iconColor = Color(0xFF581C87)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(
            modifier = Modifier
                .height(207.dp)
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

            IconButton(
                onClick = { },
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Voltar",
                    tint = Color.Black
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 50.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.grid_home3),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier.width(16.dp))

                // Nome e informações
                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    Text(
                        text = "name",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = stringResource(id = R.string.valor_perfil_artista),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Start,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.tempo_perfil_artista),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Start,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(200.dp)
                        .clickable { selectedTab = "Tattos" }
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Photo,
                            contentDescription = "Tattos",
                            tint = if (selectedTab == "Tattos") iconColor else Color.Gray,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Tattos",
                            fontSize = 14.sp,
                            color = if (selectedTab == "Tattos") iconColor else Color.Gray
                        )
                    }

                    Box(
                        modifier = Modifier
                            .width(200.dp)
                            .height(2.dp)
                            .background(if (selectedTab == "Tattos") iconColor else Color.Transparent)
                    )
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .width(200.dp)
                        .clickable { selectedTab = "Informações" }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Informações",
                            tint = if (selectedTab == "Informações") iconColor else Color.Gray,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Informações",
                            fontSize = 14.sp,
                            color = if (selectedTab == "Informações") iconColor else Color.Gray
                        )
                    }
                    Box(
                        modifier = Modifier
                            .width(200.dp)
                            .height(2.dp)
                            .background(if (selectedTab == "Informações") iconColor else Color.Transparent)
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            if (selectedTab == "Tattos") {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(1.5.dp),
                    horizontalArrangement = Arrangement.spacedBy(1.5.dp)
                ) {
                    items(
                        listOf(
                            R.drawable.tatuagem_card1,
                            R.drawable.tatuagem_card2,
                            R.drawable.tatuagem_card3,
                            R.drawable.tatuagem_card1
                        )
                    ) { imageRes ->
                        Image(
                            painter = painterResource(id = imageRes),
                            contentDescription = null
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(Color.White)
                ) {
                    item {
                        Column(
                            modifier = Modifier.padding(top = 38.dp, start = 16.dp, end = 16.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.bio_perfil_artista),
                                fontSize = 20.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(bottom = 15.dp),
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = stringResource(id = R.string.bio_texto_perfil_artista),
                                fontSize = 14.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(top = 0.dp),
                                textAlign = TextAlign.Start
                            )
                        }
                        Text(
                            text = stringResource(id = R.string.estilos_perfil_artista),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(top = 20.dp, start = 15.dp)
                        )

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            modifier = Modifier
                                .padding(8.dp)
                                .height(80.dp),
                            contentPadding = PaddingValues(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(
                                listOf(
                                    "Traço Fino",
                                    "Pontilhismo",
                                    "Aquarela",
                                    "Realismo",
                                    "Geometria",
                                    "Minimalista"
                                )
                            )
                            { estilo ->
                                // Botões não clicáveis, usando Box para simular um botão
                                Box(
                                    modifier = Modifier
                                        .background(
                                            color = colorResource(id = R.color.corBotao),
                                            shape = RoundedCornerShape(10.dp)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = estilo, color = Color.White, fontSize = 14.sp)
                                }
                            }
                        }

                        Column(
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier
                                .background(Color.White)
                                .padding(20.dp, top = 25.dp)
                                .height(600.dp)
                        ) {
                            // Linha preta para separar os blocos
                            Box(
                                modifier = Modifier
                                    .width(320.dp)
                                    .height(1.dp)
                                    .background(Color.Gray)
                            )
                            Spacer(modifier = Modifier.height(50.dp))
                            Text(
                                text = stringResource(id = R.string.endereco_ambiente_trabalho),
                                fontSize = 20.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(bottom = 15.dp),
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = stringResource(id = R.string.endereco_completo),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Gray
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                // Imagem do logo
                                Image(
                                    painter = painterResource(id = R.drawable.perfil_estudio), // substitua pela imagem do logo
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(72.dp)
                                        .clip(CircleShape)
                                )

                                Spacer(modifier = Modifier.width(16.dp))

                                // Informações da loja
                                Column {
                                    Text(
                                        modifier = Modifier.padding(start = 6.dp),
                                        text = stringResource(id = R.string.estudio_ambiente_trabalho),
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                }
                            }

                            // Descrição
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                text = stringResource(id = R.string.descricao_ambiente_trabalho),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = stringResource(id = R.string.descricao_texto_ambiente_trabalho),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Gray
                            )

                            // Linha preta para separar os blocos
                            Spacer(modifier = Modifier.height(50.dp))
                            Box(
                                modifier = Modifier
                                    .width(320.dp)
                                    .height(1.dp)
                                    .background(Color.Gray)
                            )

                            // Redes sociais
                            Spacer(modifier = Modifier.height(50.dp))
                            Text(
                                text = stringResource(id = R.string.redes_ambiente_trabalho),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = stringResource(id = R.string.instagram_ambiente_trabalho),
                                    fontSize = 14.sp,
                                    color = Color.Gray
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                // Icone do Instagram
                                IconButton(onClick = { /* ação para abrir Instagram */ }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.instagram), // substitua pelo ícone do Instagram
                                        contentDescription = "Instagram",
                                        tint = Color.Unspecified,
                                        modifier = Modifier.size(30.dp)
                                    )
                                }
                            }
                            // Botões de contato
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp, end = 10.dp, top = 30.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Button(
                                    onClick = { },
                                    modifier = Modifier.weight(1f),
                                    shape = RoundedCornerShape(8.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFA855F7)
                                    )
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Whatsapp,
                                        contentDescription = "WhatsApp",
                                        tint = Color.White,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "Conversar por WhatsApp",
                                        color = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

