package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.ui.theme.WeatherAppTheme

//Javier Alejandro Salamanca Reyes-- 00056824


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                WeatherApp()
            }
        }
    }
}

@Composable
fun WeatherApp() {
    var showDetailedScreen by remember { mutableStateOf(false) }

    if (!showDetailedScreen) {
        PantallaPrincipal(onUpdateClick = { showDetailedScreen = true })
    } else {
        PronosticoDetallado()
    }
}

@Composable
fun PantallaPrincipal(onUpdateClick: () -> Unit) {
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF72EAFF), Color(0xFF72C2FF), Color(0xFF003888)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "El Salvador",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = "25°C",
                color = Color.White,
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(32.dp))

            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Time",
                tint = Color.White.copy(alpha = 0.8f),
                modifier = Modifier.size(75.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.2f)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    WeatherDetailItem("HUM", "65%")
                    WeatherDetailItem("VIENTO", "12 km/h")
                    WeatherDetailItem("LLUVIA", "10%")
                }
            }

            Spacer(modifier = Modifier.height(45.dp))

            Button(
                onClick = onUpdateClick,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF01579B)
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .width(180.dp)
                    .height(55.dp)
            ) {
                Text(
                    text = "ACTUALIZAR",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun PronosticoDetallado() {
    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFA0B5EB),
            Color(0xFFEA52F8),
            Color(0xFF0066FF)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundGradient)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 48.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "El Salvador",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = "25°C", color = Color.White, fontSize = 56.sp, fontWeight = FontWeight.Bold)
                Text(text = "Soleado", color = Color.White.copy(alpha = 0.8f), fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(20.dp))

            EncabezadoDetallado("PRONÓSTICO POR HORAS")
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 0.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Horario("Ahora", "\u2600", "25°")
                    Horario("14:00", "\u2600", "26°")
                    Horario("16:00", "\u26C5", "24°")
                    Horario("18:00", "\u26C5", "22°")
                    Horario("20:00", "\uD83C\uDF19", "20°")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.15f)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    EncabezadoDetallado("DETALLES")
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        DetailGridItem("Humedad", "65%")
                        DetailGridItem("Viento", "12 km/h")
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        DetailGridItem("Presión", "1012 hPa")
                        DetailGridItem("UV", "5")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            EncabezadoDetallado("PRONÓSTICO SEMANAL")
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.15f)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)) {
                    WeeklyItem("Lun", "\u2600", "28°", "22°")
                    Divisor()
                    WeeklyItem("Mar", "\u26C5", "27°", "21°")
                    Divisor()
                    WeeklyItem("Mié", "\uD83C\uDF27\uFE0F", "26°", "20°")
                    Divisor()
                    WeeklyItem("Jue", "\u26C5", "25°", "19°")
                    Divisor()
                    WeeklyItem("Vie", "\uD83C\uDF26\uFE0F", "24°", "18°")
                }
            }
        }
    }
}

@Composable
fun EncabezadoDetallado(text: String) {
    Text(
        text = text,
        color = Color.White.copy(alpha = 0.7f),
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 6.dp)
    )
}

@Composable
fun Horario(time: String, emoji: String, temp: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = time, color = Color.White, fontSize = 11.sp)
        Text(
            text = emoji,
            fontSize = 20.sp,
            modifier = Modifier.padding(vertical = 2.dp)
        )
        Text(text = temp, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
    }
}

@Composable
fun DetailGridItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, color = Color.White.copy(alpha = 0.6f), fontSize = 11.sp)
        Text(text = value, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}

@Composable
fun WeeklyItem(day: String, emoji: String, max: String, min: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = day, color = Color.White, fontSize = 13.sp, modifier = Modifier.width(36.dp))
        Text(
            text = emoji,
            fontSize = 18.sp
        )
        Row {
            Text(text = max, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
            Text(text = " / ", color = Color.White.copy(alpha = 0.3f), fontSize = 13.sp)
            Text(text = min, color = Color.White.copy(alpha = 0.6f), fontSize = 13.sp)
        }
    }
}

@Composable
fun Divisor() {
    HorizontalDivider(color = Color.White.copy(alpha = 0.05f), thickness = 0.5.dp)
}

@Composable
fun WeatherDetailItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            color = Color.White.copy(alpha = 0.7f),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = value,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherPreview() {
    WeatherAppTheme {
        WeatherApp()
    }
}