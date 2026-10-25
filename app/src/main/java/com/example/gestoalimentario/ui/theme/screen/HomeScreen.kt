package com.example.gestoalimentario.ui.theme.screen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private val Verde = Color(0xFF187D60)
private val VerdeClaro = Color(0xFFF0F7ED)
private val AzulClaro = Color(0xFFEAF3FE)
private val MoradoClaro = Color(0xFFF4EAFE)
private val TextoOscuro = Color(0xFF173C32)

@Composable
fun HomeScreen(
    onDetalleClick: (Int) -> Unit,
    onCaducidadesClick: () -> Unit,
    onPerfilClick: () -> Unit,
    innerPadding: PaddingValues = PaddingValues(0.dp)
) {
    Scaffold(
        containerColor = Color(0xFFFFFEFC),
        bottomBar = {
            NavigationBar(
                containerColor = Color.White
            ) {
                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = {
                        Icon(Icons.Default.Home, contentDescription = "Inicio")
                    },
                    label = { Text("Inicio") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Verde,
                        selectedTextColor = Verde,
                        indicatorColor = VerdeClaro
                    )
                )

                NavigationBarItem(
                    selected = false,
                    onClick = onCaducidadesClick,
                    icon = {
                        Icon(
                            Icons.Default.CalendarMonth,
                            contentDescription = "Caducidades"
                        )
                    },
                    label = { Text("Caducidades") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = onPerfilClick,
                    icon = {
                        Icon(Icons.Default.Person, contentDescription = "Perfil")
                    },
                    label = { Text("Perfil") }
                )
            }
        }
    ) { scaffoldPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFFEFC))
                .padding(scaffoldPadding)
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Text(
                text = "GestoAlimentario",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = TextoOscuro
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "BIENVENIDO A",
                style = MaterialTheme.typography.labelLarge,
                color = Verde,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "GestoAlimentario",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = TextoOscuro
            )

            Text(
                text = "Organiza tus alimentos y reduce el desperdicio.",
                style = MaterialTheme.typography.bodyLarge,
                color = Color(0xFF626966)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = VerdeClaro
                )
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(68.dp)
                                .background(
                                    Color(0xFFDDEFD5),
                                    RoundedCornerShape(50)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Default.ShoppingBasket,
                                contentDescription = null,
                                tint = Verde,
                                modifier = Modifier.size(38.dp)
                            )
                        }

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Mis alimentos",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = TextoOscuro
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = "Consulta la información de tus alimentos registrados.",
                                color = Color(0xFF626966),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        Icon(
                            Icons.Default.ChevronRight,
                            contentDescription = null,
                            tint = Verde
                        )
                    }

                    Button(
                        onClick = { onDetalleClick(1) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Verde
                        ),
                        contentPadding = PaddingValues(15.dp)
                    ) {
                        Text(
                            text = "Ver detalle de ejemplo",
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Icon(
                            Icons.Default.ChevronRight,
                            contentDescription = null
                        )
                    }
                }
            }

            ShortcutCard(
                title = "Próximas caducidades",
                description = "Revisa qué alimentos caducan pronto.",
                background = AzulClaro,
                icon = {
                    Icon(
                        Icons.Default.CalendarMonth,
                        contentDescription = null,
                        tint = Color(0xFF174A79),
                        modifier = Modifier.size(32.dp)
                    )
                },
                onClick = onCaducidadesClick
            )

            ShortcutCard(
                title = "Mi perfil",
                description = "Gestiona tu información y preferencias.",
                background = MoradoClaro,
                icon = {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = null,
                        tint = Color(0xFF633D8B),
                        modifier = Modifier.size(32.dp)
                    )
                },
                onClick = onPerfilClick
            )
        }
    }
}

@Composable
private fun ShortcutCard(
    title: String,
    description: String,
    background: Color,
    icon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = background
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(58.dp)
                    .background(
                        Color.White.copy(alpha = 0.7f),
                        RoundedCornerShape(50)
                    ),
                contentAlignment = Alignment.Center
            ) {
                icon()
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF233B4C)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF5D6872)
                )
            }

            Icon(
                Icons.Default.ChevronRight,
                contentDescription = "Abrir $title",
                tint = Color(0xFF596978)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(
            onDetalleClick = {},
            onCaducidadesClick = {},
            onPerfilClick = {}
        )
    }
}
