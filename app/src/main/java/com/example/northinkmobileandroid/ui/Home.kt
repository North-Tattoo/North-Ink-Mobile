package com.example.northinkmobileandroid.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.northinkmobileandroid.R

@Composable
fun Home(modifier: Modifier = Modifier, navController: NavHostController) {
    val contexto = LocalContext.current
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .background(Color.White)
            .verticalScroll(scrollState)
    ) {
        PrimeiraSecao(contexto, navController)

    }
}

@Composable
fun PrimeiraSecao(contexto: Context, navController: NavHostController) {

    Box(
        modifier = Modifier
            .height(800.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.fundo_landing_page),
            contentDescription = "Backgroud",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Image(
            painter = painterResource(id = R.drawable.logobranca),
            contentDescription = "Logo",
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.TopStart)
                .offset(y = (-50).dp)
                .padding(20.dp)
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
                append(stringResource(id = R.string.landing_title_part_1))
                append("\n\n")
                withStyle(style = SpanStyle(color = Color(0xFF9333EA))) {
                    append(stringResource(id = R.string.landing_title_part_2))
                }
                append(" ")
                append(stringResource(id = R.string.landing_title_part_3))
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
                text = stringResource(id = R.string.landing_description),
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(bottom = 160.dp)

            )

            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp), // Espaçamento entre os botões
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally // Alinha os botões centralizados horizontalmente
            ) {
                Button(
                    onClick = { navController.navigate("listagem") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9333EA)),
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier
                        .height(60.dp)
                        .width(300.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.landing_button_text),
                        color = Color.White
                    )
                }
                Button(
                    onClick = { navController.navigate("login") },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5B5B5B)),
                    shape = RoundedCornerShape(30.dp),
                    modifier = Modifier
                        .height(60.dp)
                        .width(300.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.landing_button_tatto),
                        color = Color.White
                    )
                }
            }
        }
    }
}

