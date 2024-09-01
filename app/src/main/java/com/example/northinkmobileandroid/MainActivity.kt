package com.example.northinkmobileandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.northinkmobileandroid.ui.theme.NorthInkMobileAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NorthInkMobileAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LandingPage(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LandingPage(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
    ) {
        // Primeira seção
        Box(
            modifier = Modifier
                .fillMaxSize()
                .height(800.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.fundo_landing_page),
                contentDescription = "Backgroud",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Logo sobreposta dentro da imagem de fundo
            Image(
                painter = painterResource(id = R.drawable.logobranca),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(200.dp) // Ajuste o tamanho da logo conforme necessário
                    .align(Alignment.TopStart) // Alinha a logo no canto superior esquerdo
                    .offset(y = (-50).dp)
                    .padding(20.dp) // Adiciona espaçamento em relação às bordas
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 25.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val titleText: AnnotatedString = buildAnnotatedString {
                    append("ENCONTRE O SEU \n\n")
                    withStyle(style = SpanStyle(color = Color(0xFF9333EA))) {
                        append("TATUADOR")
                    }
                    append(" IDEAL .")
                }

                Text(
                    text = titleText,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    letterSpacing = 1.5.sp,
                    modifier = Modifier.padding(bottom = 30.dp)
                )
                Text(
                    text = "Na nossa plataforma, conectamos você aos melhores tatuadores em minutos, prontos para transformar suas ideias em tinta.\nExplore estilos únicos e faça da sua próxima tatuagem uma experiência inesquecível. ",
                    fontSize = 16.sp,
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(bottom = 160.dp)

                )

                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9333EA)),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .height(70.dp)
                        .width(230.dp)
                ) {
                    Text(text = "BUSCAR PROFISSIONAIS", color = Color.White)
                }
            }
        }

        // Segunda seção
        Box(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color(0xFF9333EA), Color(0xFFA962EA))
                    )
                )
                .fillMaxWidth()
                .padding(vertical = 16.dp)
                .height(800.dp)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val phraseText: AnnotatedString = buildAnnotatedString {
                    append("Nós celebramos a ")
                    withStyle(style = SpanStyle(color = Color(0xFF0A030F))) {
                        append("arte\n")
                    }
                    append("o que você veste.\n")
                    append("Descubra a ")
                    withStyle(style = SpanStyle(color = Color(0xFF0A030F))) {
                        append("tatuagem")
                    }
                    append(" que eleva sua essência.")
                }
                Text(
                    text = phraseText,
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(bottom = 100.dp, start = 15.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.foto_home_1),
                        contentDescription = "Foto 1",
                        modifier = Modifier
                            .size(120.dp, 160.dp)
                            .padding(horizontal = 4.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.foto_home_2),
                        contentDescription = "Foto 2",
                        modifier = Modifier
                            .size(120.dp, 160.dp)
                            .padding(horizontal = 4.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.foto_home_3),
                        contentDescription = "Foto 3",
                        modifier = Modifier
                            .size(120.dp, 160.dp)
                            .padding(horizontal = 4.dp)
                    )
                }
            }
        }

        // Terceira seção com imagem de fundo e fundo branco
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(800.dp) // Ajuste a altura conforme necessário
        ) {
            Image(
                painter = painterResource(id = R.drawable.fundo_home),
                contentDescription = "Imagem de Fundo",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .align(Alignment.Center)
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .align(Alignment.Center)
                ) {
                    val textohome: AnnotatedString = buildAnnotatedString {
                        append("Descubra os ")
                        withStyle(style = SpanStyle(color = Color(0xFF0A030F))) {
                            append("artistas")
                        }
                        append(" que não apenas ouvem, mas entendem sua ")
                        withStyle(style = SpanStyle(color = Color(0xFF0A030F))) {
                            append("história")
                        }
                        append(".")
                    }
                    Text(
                        text = textohome,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 100.dp)
                    )

                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 200.dp)
                    ){
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.grid_home2),
                                contentDescription = "Foto 1",
                                modifier = Modifier
                                    .size(100.dp, 120.dp)
                                    .clip(RoundedCornerShape(16.dp))
                            )
                            Text(
                                text = "King Tatto",
                                fontSize = 14.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.grid_home3),
                                contentDescription = "Foto 2",
                                modifier = Modifier
                                    .size(100.dp, 120.dp)
                                    .clip(RoundedCornerShape(16.dp))
                            )
                            Text(
                                text = "Lotus Atelier",
                                fontSize = 14.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.grid_home4),
                                contentDescription = "Foto 3",
                                modifier = Modifier
                                    .size(100.dp, 120.dp)
                                    .clip(RoundedCornerShape(16.dp))
                            )
                            Text(
                                text = "Route Tatto",
                                fontSize = 14.sp,
                                color = Color.Black,
                                modifier = Modifier.padding(top = 8.dp)
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
        LandingPage()
    }
}