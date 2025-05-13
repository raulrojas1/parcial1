package com.example.parcialcalculadora

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parcialcalculadora.ui.theme.ParcialCalculadoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ParcialCalculadoraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    MainContent(Modifier.padding(paddingValues))
                }
            }
        }
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    var inputNota by remember { mutableStateOf("") }
    val contexto = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFADD8E6))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Primer Parcial", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Autores:")
        Text("Raul Rojas 9-756-1052")
        Text("Daniel Tovares 8-992-1618")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = inputNota,
            onValueChange = { texto ->
                if (texto.all { it.isDigit() }) {
                    inputNota = texto
                }
            },
            label = { Text("Digite la nota a evaluar") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val valorNota = inputNota.toIntOrNull()
                val mensaje = when {
                    valorNota == null || valorNota < 0 || valorNota > 100 -> "Nota no válida"
                    valorNota >= 91 -> "A (Excelente)"
                    valorNota >= 81 -> "B (Bueno)"
                    valorNota >= 71 -> "C (Regular)"
                    valorNota >= 61 -> "D (Mas o menos regular)"
                    else -> "No aprobado"
                }

                Toast.makeText(contexto, "Resultado: $mensaje", Toast.LENGTH_LONG).show()
            },
            shape = RoundedCornerShape(50),
            modifier = Modifier.width(200.dp)
        ) {
            Text("Evaluar")
        }
    }
}
