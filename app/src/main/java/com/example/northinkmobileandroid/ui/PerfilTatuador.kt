package com.example.northinkmobileandroid.ui

import android.content.Intent
import android.net.Uri
import android.util.Log
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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import coil.compose.rememberImagePainter
import com.example.northinkmobileandroid.R
import com.example.northinkmobileandroid.api.service.UploadService
import com.example.northinkmobileandroid.viewmodel.TatuadorViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
fun PerfilTatuador(
    modifier: Modifier = Modifier,
    navController: NavController,
    tatuadorId: Long,
    tatuadorViewModel: TatuadorViewModel,
    uploadService: UploadService = UploadService()
) {
    Log.d("PerfilTatuador", "Recebendo userId na tela: $tatuadorId")
    var selectedTab by remember { mutableStateOf("Tattos") }

    val iconColor = Color(0xFF581C87)
    val context = LocalContext.current

    val tatuadorPortfolio by tatuadorViewModel.tatuadorPortfolio.observeAsState()

    val error by tatuadorViewModel.error.observeAsState()

    var profilePictureUrl by remember { mutableStateOf<String?>(null) }
    var portfolioImages by remember { mutableStateOf<List<String>>(emptyList()) }

    val portfolio = tatuadorPortfolio

    LaunchedEffect(tatuadorId) {
        try {
            Log.d("PerfilTatuador", "Buscando dados para o tatuadorId: $tatuadorId")

            // Carregar o portfólio
            tatuadorViewModel.getTatuadorPortfolio(tatuadorId)

            val userName = tatuadorPortfolio?.nome?.trim() ?: "usuario"

            // Buscar imagens do portfólio diretamente pelo UploadService
            val portfolioFolder = "${tatuadorId}_${userName}/tattos_images"
            portfolioImages = withContext(Dispatchers.IO) {
                uploadService.buscarImagensDaPastaCloudinary(portfolioFolder)
            }

            Log.d("PerfilTatuador", "imagens: $profilePictureUrl")

            // Buscar imagem de perfil
            val profilePictureFolder = "${tatuadorId}_${userName}/profile_picture"
            profilePictureUrl = withContext(Dispatchers.IO) {
                uploadService.buscarImagensDaPastaCloudinary(profilePictureFolder).firstOrNull()

            }
        } catch (e: Exception) {
            Log.e("PerfilTatuador", "Erro ao buscar dados: ${e.message}")
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
//        Cabeçalho do perfil
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
                onClick = {
                    navController.popBackStack()
                },
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
                // Exibe o indicador de carregamento enquanto a imagem não for carregada
                if (profilePictureUrl == null) {

                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(Color.Gray.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(40.dp),
                            color = iconColor
                        )
                    }
                } else {
                    // Exibe a imagem de perfil quando a URL estiver disponível
                    Image(
                        painter = rememberImagePainter(
                            data = profilePictureUrl,
                            builder = {
                                // Aqui podemos usar um carregamento padrão, sem a necessidade de placeholder.
                            }
                        ),
                        contentDescription = "Foto de perfil",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                    )
                }

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
                        text = "Preço Mínimo: R$ ${portfolio?.valorMin ?: "N/A"}",
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
                    if (portfolioImages.isEmpty()) {
                        Text(
                            text = "Nenhuma imagem encontrada.",
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            color = Color.Gray
                        )
                    } else {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(1.5.dp),
                            horizontalArrangement = Arrangement.spacedBy(1.5.dp)
                        ) {
                            items(portfolioImages) { imageUrl ->
                                Image(
                                    painter = rememberImagePainter(
                                        data = imageUrl,
                                        builder = {
                                            crossfade(true)
                                            placeholder(R.drawable.grid_home3)
                                            error(R.drawable.tatuagem_card3)
                                        }
                                    ),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(150.dp)
                                        .clip(RoundedCornerShape(8.dp)),
                                    contentScale = ContentScale.Crop
                                )
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
                                items(portfolio.estilos) { estilo ->
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
                                IconButton(onClick = {
                                    val instagramUrl = "https://www.instagram.com/${portfolio?.instagram}"

                                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(instagramUrl))
                                    context.startActivity(intent)
                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.instagram),
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
                                    .padding(
                                        start = 10.dp,
                                        end = 10.dp,
                                        top = 30.dp,
                                        bottom = 30.dp
                                    ),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Button(
                                    onClick = {
                                        val numeroWhatsApp = portfolio?.telefone
                                        val mensagem = "Olá, gostaria de saber mais sobre seus serviços!"

                                        val numeroWhatsAppFormatado =
                                            numeroWhatsApp?.replace(Regex("[^0-9]"), "")

                                        val whatsappUrl = "https://wa.me/+55${numeroWhatsAppFormatado}?text=${Uri.encode(mensagem)}"

                                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(whatsappUrl))

                                        context.startActivity(intent)
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




