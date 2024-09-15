package com.example.northinkmobileandroid

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Whatsapp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.northinkmobileandroid.ui.theme.NorthInkMobileAndroidTheme

class EntrarContato : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NorthInkMobileAndroidTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    ContactForm(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ContactForm(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var interest by remember { mutableStateOf("") }
    val contexto = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Top bar with back button and text
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { val telaPerfilArtista = Intent(contexto, PerfilArtista::class.java)

                contexto.startActivity(telaPerfilArtista)
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIosNew, // Substitua isso pelo ícone correto
                    contentDescription = "Voltar",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.width(1.dp))
            Text(text = "Voltar", style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Contact message
        Text(
            text = stringResource(id = R.string.fale_entrar_contato),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        // Inputs for name, phone, and interest
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nome") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Telefone") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = interest,
                onValueChange = { interest = it },
                label = { Text("Seu interesse") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 6
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // WhatsApp logo and "Entrar em contato" button
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* ação de contato */ },
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.corBotao)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    // Deixar o botão mais quadrado com bordas arredondadas
                    .height(60.dp)
                    .width(170.dp)
            ) {
                Text(text = stringResource(id = R.string.contato_perfil_artista))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ContactFormPreview() {
    NorthInkMobileAndroidTheme {
        ContactForm()
    }
}
