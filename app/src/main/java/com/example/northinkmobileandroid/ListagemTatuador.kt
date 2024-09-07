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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.northinkmobileandroid.ui.theme.NorthInkMobileAndroidTheme
import java.time.format.TextStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.northinkmobileandroid.ui.theme.NorthInkMobileAndroidTheme
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.unit.Dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue


class ListagemTatuador : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NorthInkMobileAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TelaListagemTatuador(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun TelaListagemTatuador(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .height(800.dp)
        ){
            Image(
                painter = painterResource(id = R.mipmap.fundo_listagem_),
                contentDescription = "Backgroud Listagem",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Image(
                painter = painterResource(id = R.drawable.logobranca),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.TopStart)
                    .offset(y = (-30).dp)
                    .padding(20.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.listagem_titulo),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 32.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                )  {
                    CustomOutlinedTextField(
                        label = stringResource(id = R.string.label_listagem1),
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    )
                    CustomOutlinedTextField(
                        label = stringResource(id = R.string.label_listagem2),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                    )
                }
            }
        }
        // Segunda seção
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(800.dp)
        ){
            Image(
                painter = painterResource(id = R.mipmap.fundo_listagem2),
                contentDescription = "Imagem de fundo navegação",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(id = R.string.subtitulo_listagem),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 16.dp, top = 120.dp)
            )
            SimpleImageCarousel(
                images = listOf(R.mipmap.aquarela, R.mipmap.blackwork, R.mipmap.neo_traditional, R.mipmap.geometrico2,
                    R.mipmap.minimalista, R.mipmap.lettering, R.mipmap.new_school, R.mipmap.old_school, R.mipmap.oriental2,
                    R.mipmap.trash_polka, R.mipmap.pontilhismo3, R.mipmap.realismo), // IDs das imagens
                imageDescriptions = listOf("Aquarela", "Blackwork", "Neo Traditional", "Geometrico",
                    "Minimalista", "Lettering","New School", "Old School", "Oriental", "Trash Polka",
                    "Pontilhismo", "Realismo") // Descrições das imagens
            )
        }
        SessaoCardsTatuadores()
    }
}
@Composable
fun SessaoCardsTatuadores() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(800.dp)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Os melhores profissionais em um só lugar.",
            color = Color(0xFFA855F7),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Exemplo de Card de Profissional
        CardProfissional(
            nome = "João Silva",
            endereco = "Rua das Flores, 123 - São Paulo, SP",
            estilos = listOf("Blackwork", "Aquarela", "Realismo"),
            fotoTatuador = R.drawable.foto_perfil, // ID do recurso da foto do tatuador
            fotosTatuagens = listOf(R.drawable.tatuagem_card1, R.drawable.tatuagem_card2, R.drawable.tatuagem_card3) // IDs das tatuagens
        )
    }
}

@Composable
fun CardProfissional(
    nome: String,
    endereco: String,
    estilos: List<String>,
    fotoTatuador: Int,
    fotosTatuagens: List<Int>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(0xFFE9EDF3)),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Fotos das Tatuagens
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                fotosTatuagens.forEach { foto ->
                    Image(
                        painter = painterResource(id = foto),
                        contentDescription = "Foto da tatuagem",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Informações do Tatuador
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = fotoTatuador),
                    contentDescription = "Foto do Tatuador",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = nome,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = endereco,
                        fontSize = 14.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Lista de Estilos
                    estilos.forEach { estilo ->
                        Text(
                            text = estilo,
                            fontSize = 14.sp,
                            color = Color.DarkGray
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(label: String, modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(label) },
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFFA855F7),  // Borda roxa quando focado
            unfocusedBorderColor = Color(0xFFA855F7),  // Borda roxa quando não focado
            focusedLabelColor = Color(0xFFA855F7),  // Label roxa quando focado
            unfocusedLabelColor = Color.Gray,  // Label cinza quando não focado
            containerColor = Color.White,  // Cor de fundo branco
            focusedTextColor = Color.Black,  // Cor do texto digitado
            unfocusedTextColor = Color.Black

        ),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
    )
}

@Composable
fun SimpleImageCarousel(
    images: List<Int>, // Lista de IDs de recursos de imagem
    imageDescriptions: List<String> // Lista de descrições para as imagens
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        images.forEachIndexed { index, image ->

            var isTextVisible by remember { mutableStateOf(false) }

            Box(
                modifier = Modifier
                    .padding(8.dp, top = 180.dp, end = 8.dp)
                    .size(150.dp) // Ajuste o tamanho conforme necessário
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Black.copy(alpha = 0.6f)) // Adiciona sombra
                    .clickable { isTextVisible = !isTextVisible } // Alterna visibilidade ao clicar
            ) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = imageDescriptions[index],
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                
                if (isTextVisible){
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.8f))
                    ){
                        Text(
                            text = imageDescriptions[index],
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .align(Alignment.Center),
                                fontSize = 18.sp
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    NorthInkMobileAndroidTheme {
        TelaListagemTatuador()
    }
}