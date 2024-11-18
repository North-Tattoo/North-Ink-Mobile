package com.example.northinkmobileandroid.ui

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.northinkmobileandroid.R
import com.example.northinkmobileandroid.api.service.UploadService
import com.example.northinkmobileandroid.viewmodel.TatuadorViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File


@Composable
fun GerenciamentoConta(
    modifier: Modifier = Modifier,
    navController: NavController,
    tatuadorViewModel: TatuadorViewModel
) {
    var selectedTab by remember { mutableStateOf("Tattos") }

    val iconColor = Color(0xFF581C87)
    val context = LocalContext.current

    val portfolio by tatuadorViewModel.tatuadorPortfolioLogado.observeAsState()
    val error by tatuadorViewModel.error.observeAsState()


    var uploadUrl by remember { mutableStateOf<String?>(null) }  // Guarda a URL da imagem no Cloudinary
    var uploadError by remember { mutableStateOf<Boolean?>(null) }

    val coroutineScope = rememberCoroutineScope()

    val selectedImageUris = remember { mutableStateListOf<Uri>() }

    var showToast by remember { mutableStateOf(false) }

    val uploadService = remember { UploadService() }

    // Chama a função para carregar o portfólio ao compor a tela
    LaunchedEffect(Unit) {
        tatuadorViewModel.carregarTatuadorPortfolio()
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            uri?.let {
                selectedImageUris.add(it) // Adiciona a imagem selecionada à lista
            }
        }
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
//        Cabeçalho do perfil
        Column(
            modifier = Modifier
                .height(190.dp)
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
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Voltar",
                        tint = Color.Black
                    )

                }
                IconButton(
                    onClick = {
                        navController.navigate("gerenciamentoContaEdicao")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Editar Informações",
                        tint = Color.Black
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 50.dp, end = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
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
                        text = "${portfolio?.nome ?: "Nome N/A"} ${portfolio?.sobrenome ?: "N/A"}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text =  "Preço mínimo: R$ ${portfolio?.valorMin ?: "N/A"}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Start,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Text(
                        text = "Experiência: ${portfolio?.anosExperiencia ?: "N/A"}",
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
//        Sessão de tatuagem
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
                    verticalArrangement = Arrangement.spacedBy(1.4.dp),
                    horizontalArrangement = Arrangement.spacedBy(1.5.dp)
                ) {
                    items(selectedImageUris) { uri ->
                        val painter = rememberImagePainter(data = uri)
                        Image(
                            painter = painter,
                            contentDescription = "Imagem selecionada",
                            modifier = Modifier
                                .size(150.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.Gray)
                        )
                    }

                    // Adiciona um quadrado com símbolo de upload
                    item {
                        Box(
                            modifier = Modifier
                                .size(150.dp) // Certifique-se de que o tamanho é igual ao das outras imagens
                                .background(Color.LightGray)
                                .clickable {
                                    launcher.launch("image/*")
                                    },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Upload,
                                contentDescription = "Upload de imagem",
                                tint = Color.Gray,
                                modifier = Modifier.size(48.dp)
                            )
                        }
                        // Chamada do upload dentro de uma coroutine
                        coroutineScope.launch(Dispatchers.IO) {
                            val files =
                                selectedImageUris.mapNotNull { uri -> uriToFile(context, uri) }
                            if (files.isNotEmpty()) {
                                val uploadedUrls =
                                    uploadService.uploadImagesToCloudinary(context, files)
                                withContext(Dispatchers.Main) {
                                    if (uploadedUrls.isNotEmpty()) {
                                        Toast.makeText(
                                            context,
                                            "Upload bem-sucedido de ${uploadedUrls.size} imagens!",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Erro no upload!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        }

                        Column(modifier = modifier.fillMaxSize()) {
                            // Feedback do upload
                            when {
                                uploadUrl != null && !showToast -> {
                                    Toast.makeText(context, "Upload bem-sucedido! URL: $uploadUrl", Toast.LENGTH_LONG).show()
                                    showToast = true
                                    uploadUrl = null
                                }
                                uploadError == true && !showToast -> {
                                    Toast.makeText(context, "Erro no upload!", Toast.LENGTH_SHORT).show()
                                    showToast = true
                                    uploadError = null
                                }
                            }
                        }
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
                            portfolio?.resumo?.let {
                                Text(
                                    text = it,
                                    fontSize = 14.sp,
                                    color = Color.Black,
                                    modifier = Modifier.padding(top = 0.dp),
                                    textAlign = TextAlign.Start
                                )
                            }
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
                            if (portfolio != null) {
                                items(portfolio!!.estilos) { estilo ->
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                color = colorResource(id = R.color.corBotao),
                                                shape = RoundedCornerShape(10.dp)
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = estilo.nome,
                                            color = Color.White,
                                            fontSize = 14.sp
                                        )
                                    }
                                }
                            }
                        }

                        Column(
                            horizontalAlignment = Alignment.Start,
                            modifier = Modifier
                                .background(Color.White)
                                .padding(20.dp)
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
                                text = "${portfolio?.estudio?.endereco?.rua ?: "Endereço não disponível"}, ${portfolio?.estudio?.endereco?.numero} - ${portfolio?.estudio?.endereco?.bairro}, ${portfolio?.estudio?.endereco?.cidade} - ${portfolio?.estudio?.endereco?.estado}, ${portfolio?.estudio?.endereco?.cep }",
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
                                    portfolio?.estudio?.let {
                                        Text(
                                            modifier = Modifier.padding(start = 6.dp),
                                            text = it.nome ?: "Nome não disponível",
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.Black
                                        )
                                    }
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
                            portfolio?.estudio?.let {
                                Text(
                                    text = it.descricao ?: "Descrição não disponível",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Gray
                                )
                            }
                            Spacer(modifier = Modifier.height(50.dp))
                        }
                    }
                }
            }
        }
    }
}

// Função auxiliar para converter Uri para File
fun uriToFile(context: Context, uri: Uri): File? {
    val contentResolver = context.contentResolver
    val tempFile = File(context.cacheDir, "temp_image")
    contentResolver.openInputStream(uri)?.use { inputStream ->
        tempFile.outputStream().use { outputStream ->
            inputStream.copyTo(outputStream)
        }
    }
    return tempFile.takeIf { it.exists() }
}

