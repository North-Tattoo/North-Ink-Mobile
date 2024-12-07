package com.example.northinkmobileandroid.ui

import android.util.Log
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Paid
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import android.widget.Toast
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.northinkmobileandroid.data.model.Estilo
import com.example.northinkmobileandroid.R
import com.example.northinkmobileandroid.api.RetrofitInstance
import com.example.northinkmobileandroid.api.service.UploadService
import com.example.northinkmobileandroid.data.model.TatuadorListagem
import com.example.northinkmobileandroid.data.model.Tatuagem
import com.example.northinkmobileandroid.viewmodel.TatuadorViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun ListagemTatuador(navController: NavHostController, modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    val tatuadores = remember { mutableStateOf<List<TatuadorListagem>>(emptyList()) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        try {
            tatuadores.value = RetrofitInstance.tatuadorApi.getTatuadores()
        } catch (e: Exception) {
            Toast.makeText(context, "Erro ao carregar tatuadores: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .height(750.dp)
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
                    .offset(y = (-50).dp)
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
                .height(750.dp)
        ){
            Image(
                painter = painterResource(id = R.mipmap.fundo_listagem2),
                contentDescription = "Imagem de fundo navegação",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(id = R.string.subtitulo_listagem),
                fontSize = 25.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 16.dp, top = 120.dp)
            )
            SimpleImageCarousel(
                images = listOf(
                    R.mipmap.aquarela, R.mipmap.blackwork, R.mipmap.neo_traditional, R.mipmap.geometrico2,
                    R.mipmap.minimalista, R.mipmap.lettering, R.mipmap.new_school, R.mipmap.old_school, R.mipmap.oriental2,
                    R.mipmap.trash_polka, R.mipmap.pontilhismo3, R.mipmap.realismo
                ), // IDs das imagens
                imageDescriptions = listOf("Aquarela", "Blackwork", "Neo Traditional", "Geometrico",
                    "Minimalista", "Lettering","New School", "Old School", "Oriental", "Trash Polka",
                    "Pontilhismo", "Realismo")
            )
        }
        Text(
            text = stringResource(id = R.string.subtitulo_card),
            color = Color(0xFFA855F7),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 30.dp, bottom = 60.dp, start = 15.dp)
        )

        if (tatuadores.value.isNotEmpty()) {
            tatuadores.value.forEach { tatuador ->
                SessaoCardsTatuadores(tatuador, navController)
            }
        } else {
            Text(
                text = "Nenhum tatuador encontrado.",
                color = Color.Black,
                fontSize = 18.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun SessaoCardsTatuadores(
    tatuador: TatuadorListagem,
    navController: NavHostController
) {
    val imagensPortifolio = remember { mutableStateOf<List<String>>(emptyList()) }
    var profilePictureUrl by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(tatuador.id) {
        val userId = tatuador.id
        val userName = tatuador.nome.trim()
        try {
            val folderPathPortfolio = "tatuadores/$userId/$userName/tattoos"
            val folderPathProfile = "tatuadores/$userId/$userName/profile_picture"

            withContext(Dispatchers.IO) {
                imagensPortifolio.value = UploadService().buscarImagensDaPastaCloudinary(folderPathPortfolio)
                val imagensProfile = UploadService().buscarImagensDaPastaCloudinary(folderPathProfile)
                profilePictureUrl = imagensProfile.firstOrNull()
            }
        } catch (e: Exception) {
            Log.e("SessaoCardsTatuadores", "Erro ao buscar imagens: ${e.message}")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .height(700.dp)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Exemplo de Card de Profissional
        CardProfissional(
            navController = navController,
            id = tatuador.id,
            nome = tatuador.nome ?: "N/A",
            sobrenome = tatuador.sobrenome ?: "N/A",
            rua = tatuador.estudio?.endereco?.rua ?: "Endereço indisponível",
            numero = tatuador.estudio?.endereco?.numero?: 0,
            precoMinimo = tatuador.precoMin ?: 0.0,
            estilos = tatuador.estilos,
            fotoTatuador =  profilePictureUrl,
            fotosTatuagens = imagensPortifolio.value.take(3)

        )
    }
}

@Composable
fun CardProfissional(
    navController: NavHostController,
    id: Long,
    nome: String,
    sobrenome: String?,
    rua: String?,
    numero: Int?,
    precoMinimo: Double?,
    estilos: List<Estilo>,
    fotoTatuador: String?,
    fotosTatuagens: List<String>?,

    ) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .size(width = 400.dp, height = 600.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFDADADA)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Fotos das Tatuagens
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (fotosTatuagens.isNullOrEmpty()) {
                    Text(
                        text = "Carregando imagens...",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(16.dp)
                    )
                } else {
                    fotosTatuagens.forEach { imagemUrl ->
                        val painter = rememberImagePainter(data = imagemUrl)
                        Image(
                            painter = painter,
                            contentDescription = "Foto da tatuagem",
                            modifier = Modifier
                                .size(320.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

            }

            Spacer(modifier = Modifier.height(30.dp))
            // Informações do Tatuador
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (fotoTatuador == null) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(Color.Gray.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = Color(0xFFA855F7))
                    }
                } else {
                    Image(
                        painter = rememberImagePainter(data = fotoTatuador),
                        contentDescription = "Foto do Tatuador",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = "$nome ${sobrenome}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.LocationOn,
                            contentDescription = "Localização",
                            tint = Color(0xFFA855F7),
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${rua}, ${numero}",
                            fontSize = 14.sp,
                            color = Color.Black
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Paid,
                            contentDescription = "Taxa",
                            tint = Color(0xFFA855F7),
                            modifier = Modifier.size(15.dp)

                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Preço mínimo: ${precoMinimo}",
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium,

                        )
                    }
                }
            }

                    Spacer(modifier = Modifier.height(30.dp))

                    // Lista de Estilos
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        estilos.forEach { estilo ->
                            Box(
                                modifier = Modifier
                                    .background(Color(0xFF581C87), RoundedCornerShape(8.dp))
                                    .widthIn(min = 90.dp)
                            ){
                                Text(
                                    text = estilo.nome,
                                    fontSize = 14.sp,
                                    color = Color.White,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                )
                            }
                    }
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Botões
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {

                       },
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
                        text = "Conversar",
                        color = Color.White
                    )
                }
                Button(
                    onClick = {
                        navController.navigate("perfilTatuador/${id}")
                        Log.d("ListagemTatuador", "Navegando para PerfilTatuador com userId: $id")
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFA855F7)
                    )
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Perfil",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Ver Perfil",
                        color = Color.White
                    )
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
            focusedBorderColor = Color(0xFFA855F7),
            unfocusedBorderColor = Color(0xFFA855F7),
            focusedLabelColor = Color(0xFFA855F7),
            unfocusedLabelColor = Color.Gray,
            containerColor = Color.White,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black

        ),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
    )
}

@Composable
fun SimpleImageCarousel(
    images: List<Int>,
    imageDescriptions: List<String>
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
                    .size(250.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Black.copy(alpha = 0.6f))
                    .clickable { isTextVisible = !isTextVisible }
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



