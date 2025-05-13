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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ParcialContent(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ParcialContent(modifier: Modifier = Modifier) {
    var notaText by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFADD8E6))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Parcial #1", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Raul Rojas")
        Text("Daniel Tovares")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = notaText,
            onValueChange = { input ->
                if (input.all { it.isDigit() }) {
                    notaText = input
                }
            },
            label = { Text("Ingrese la nota a validar") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val nota = notaText.toIntOrNull()
                val resultado = when {
                    nota == null || nota < 0 || nota > 100 -> "Nota inválida"
                    nota >= 91 -> "A (Excelente)"
                    nota >= 81 -> "B (Bueno)"
                    nota >= 71 -> "C (Regular)"
                    nota >= 61 -> "D (Más o menos regular)"
                    else -> "No Aprobado"
                }

                Toast.makeText(context, "Resultado: $resultado", Toast.LENGTH_LONG).show()
            },
            shape = RoundedCornerShape(50),
            modifier = Modifier.width(200.dp)
        ) {
            Text("Validar")
        }
    }
}
