package com.example.northinkmobileandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.northinkmobileandroid.ui.theme.NorthInkMobileAndroidTheme
import androidx.compose.material3.IconButton
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource

class AmbienteTrabalho : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NorthInkMobileAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TelaAmbienteTrabalho(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TelaAmbienteTrabalho(modifier: Modifier = Modifier) {
    // Fundo claro para toda a tela
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.bg_north))
    ) {
        // Centralizar o bloco
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            // Título
            Text(
                text = stringResource(id = R.string.trabalho_ambiente_trabalho),
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3C3C3C),
                modifier = Modifier.padding(top = 24.dp, bottom = 16.dp),
                textAlign = TextAlign.Center
            )

            // Cartão com informações do ambiente
            // Deixar um shadow para destacar o cartão


            Card(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .shadow(14.dp * 2)
//                    .height(600.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .background(Color.White)
                        .padding(20.dp, top = 25.dp)
                        .height(600.dp)
                ) {
                    // Logo e informações da loja
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
                    Spacer(modifier = Modifier.height(25.dp))
                    Text(
                        text = stringResource(id = R.string.endereco_ambiente_trabalho),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(id = R.string.endereco_completo),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
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
                                tint = Color.Unspecified, // Para preservar as cores originais do ícone
                                modifier = Modifier.size(30.dp)
                            )

                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewAmbienteTrabalho() {
    NorthInkMobileAndroidTheme {
        TelaAmbienteTrabalho()
    }
}
