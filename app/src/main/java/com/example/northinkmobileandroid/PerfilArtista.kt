package com.example.northinkmobileandroid

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.Portrait
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.northinkmobileandroid.ui.theme.NorthInkMobileAndroidTheme
import androidx.compose.material3.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource

class PerfilArtista : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NorthInkMobileAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TelaPerfilArtista(
                        name = stringResource(id = R.string.card_nome),
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TelaPerfilArtista(name: String, modifier: Modifier = Modifier) {
    val contexto = LocalContext.current
    // Fundo claro para toda a tela
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.bg_north))
    ) {
        }

        // Centralizar o bloco com gradiente
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Text(
                text = stringResource(id = R.string.titulo_perfil_artista),
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3C3C3C),
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 20.dp),
                textAlign = TextAlign.Center
            )

            // Cartão com gradiente
            Card(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .width(700.dp)
                    .shadow(14.dp * 2)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color(0xFF9333EA),
                                    Color(0xFFFEC0FF)
                                )
                            )
                        )
                        .height(700.dp)
                        .padding(10.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.Start
                    ) {
                        // ROW para separar a imagem do artista e as informações
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                                .padding(start = 10.dp, top = 25.dp, bottom = 15.dp)
                        ) {
                            // Imagem do Artista
                            Image(
                                painter = painterResource(id = R.drawable.grid_home3), // substitua com sua imagem
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(CircleShape)
                            )

                            Spacer(modifier = Modifier.width(16.dp))

                            // Nome e informações
                            Column(
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.weight(1f),
                            ) {
                                Text(
                                    text = name,
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
//                                  color = colorResource(id = R.color.text_primario),
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }

                        // Bio do artista
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
                            Spacer(modifier = Modifier.width(8.dp)) // Espaço entre os textos
                            Text(
                                text = stringResource(id = R.string.bio_texto_perfil_artista),
                                fontSize = 14.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(top = 0.dp), // Ajuste o padding conforme necessário
                                textAlign = TextAlign.Start
                            )
                        }

                        // Estilos
                        Text(
                            text = stringResource(id = R.string.estilos_perfil_artista),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(top = 20.dp, start = 15.dp)

                        )


                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3), // Define 3 colunas na grade
                            modifier = Modifier
                                .padding(8.dp)
                                .height(80.dp), // Ajuste a altura conforme necessário
                            contentPadding = PaddingValues(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(listOf("Traço Fino", "Pontilhismo", "Aquarela", "Realismo", "Geometria", "Minimalista")) { estilo ->
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

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp, top = 30.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = { val telaEntrarContato = Intent(contexto, EntrarContato::class.java)

                                    contexto.startActivity(telaEntrarContato) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFA855F7)
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Whatsapp, // Substitua isso pelo ícone correto
                                    contentDescription = "WhatsApp",
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Conversar",
                                    color = Color.White
                                )
                            }
                            Button(
                                onClick = { val telaFeedArtista = Intent(contexto, FeedArtista::class.java)

                                    contexto.startActivity(telaFeedArtista) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFFA855F7)
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Photo, // Substitua isso pelo ícone correto
                                    contentDescription = "Fotos",
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Ver Portfólio",
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
//}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview3() {
    NorthInkMobileAndroidTheme {
        TelaPerfilArtista(name = stringResource(id = R.string.card_nome))
    }
}