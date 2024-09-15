package com.example.northinkmobileandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.northinkmobileandroid.ui.theme.NorthInkMobileAndroidTheme

class FeedArtista : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NorthInkMobileAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TelaFeedArtista(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun TelaFeedArtista(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp)
    ) {
        // Título da página
        Text(
            text = stringResource(id = R.string.titulo_feed_artista),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3C3C3C),
            modifier = Modifier
                .padding(bottom = 16.dp, top = 30.dp)
                .align(Alignment.CenterHorizontally)
        )

        // Grade de tatuagens ocupando 60-70% da tela
        LazyVerticalGrid(
            columns = GridCells.Fixed(2), // Definir 2 colunas
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier
                .fillMaxHeight(0.9f) // Ocupa 70% da altura da tela
                .fillMaxWidth()
        ) {
            items(6) { index ->
                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .aspectRatio(0.75f) // Proporção altura/largura ajustada para imagens mais "longas"
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Imagem da tatuagem
                        Image(
                            painter = painterResource(id = R.drawable.tatuagem_card2), // Substitua pelo ID da imagem real
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )

                        // Adicionar preço à primeira imagem
                        if (index == 0) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color(0x99000000)) // Fundo transparente preto
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Preço\nR$ 300",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
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

@Preview(showBackground = true)
@Composable
fun PreviewFeedArtista() {
    NorthInkMobileAndroidTheme {
        TelaFeedArtista()
    }
}