package com.example.gestoalimentario.ui.theme.screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gestoalimentario.R

private val VerdeCaducidad = Color(0xFF187D60)
private val FondoCaducidad = Color(0xFFFFFEFC)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaducidadesScreen(
    onVolver: () -> Unit
) {
    Scaffold(
        containerColor = FondoCaducidad,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "GestoAlimentario",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onVolver) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFEEF6EB)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 18.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Próximas caducidades",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF172D27)
            )

            Text(
                text = "Estos alimentos caducan pronto.",
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF606966)
            )

            ExpirationItem(
                nombre = "Tomate",
                fecha = "15/11/2026",
                dias = "En 3 días",
                fondoEtiqueta = Color(0xFFFFE1E0),
                colorEtiqueta = Color(0xFFC62828),
                imageRes = R.drawable.tomate
            )

            ExpirationItem(
                nombre = "Leche",
                fecha = "18/11/2026",
                dias = "En 6 días",
                fondoEtiqueta = Color(0xFFFFF0C2),
                colorEtiqueta = Color(0xFF8A6200),
                imageRes = R.drawable.leche
            )

            ExpirationItem(
                nombre = "Pollo",
                fecha = "20/11/2026",
                dias = "En 8 días",
                fondoEtiqueta = Color(0xFFFFF0C2),
                colorEtiqueta = Color(0xFF8A6200),
                imageRes = R.drawable.pollo
            )

            ExpirationItem(
                nombre = "Lechuga",
                fecha = "25/11/2026",
                dias = "En 13 días",
                fondoEtiqueta = Color(0xFFDDF4E3),
                colorEtiqueta = Color(0xFF237342),
                imageRes = R.drawable.lechuga
            )

            ExpirationItem(
                nombre = "Plátano",
                fecha = "28/11/2026",
                dias = "En 16 días",
                fondoEtiqueta = Color(0xFFDDF4E3),
                colorEtiqueta = Color(0xFF237342),
                imageRes = R.drawable.platano
            )
        }
    }
}

@Composable
private fun ExpirationItem(
    nombre: String,
    fecha: String,
    dias: String,
    fondoEtiqueta: Color,
    colorEtiqueta: Color,
    imageRes: Int? = null
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(58.dp)
                    .background(
                        Color(0xFFF1F3EF),
                        RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                if (imageRes != null) {
                    Image(
                        painter = painterResource(imageRes),
                        contentDescription = nombre,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = null,
                        tint = VerdeCaducidad,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = nombre,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = fecha,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF606966)
                )
            }

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            fondoEtiqueta,
                            RoundedCornerShape(50)
                        )
                        .padding(
                            horizontal = 10.dp,
                            vertical = 6.dp
                        )
                ) {
                    Text(
                        text = dias,
                        color = colorEtiqueta,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Ver $nombre",
                tint = Color(0xFF606966)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CaducidadesScreenPreview() {
    MaterialTheme {
        CaducidadesScreen(onVolver = {})
    }
}
