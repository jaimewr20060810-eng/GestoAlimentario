
package com.example.gestoalimentario.ui.theme.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

private val VerdePerfil = Color(0xFF00866A)
private val FondoPerfil = Color(0xFFF7FAF8)
private val TextoPerfil = Color(0xFF172A3A)

@Composable
fun PerfilScreen(
    onVolver: () -> Unit,
    onInicioClick: () -> Unit = {},
    onCaducidadesClick: () -> Unit = {}
) {
    Scaffold(
        containerColor = FondoPerfil,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "GestoAlimentario",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF124D48)
                )

                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Configuración",
                    tint = Color(0xFF124D48)
                )
            }
        },
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                NavigationBarItem(
                    selected = false,
                    onClick = onInicioClick,
                    icon = {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = "Inicio"
                        )
                    },
                    label = { Text("Inicio") }
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
                    selected = true,
                    onClick = {},
                    icon = {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Perfil"
                        )
                    },
                    label = { Text("Perfil") }
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .size(92.dp)
                    .background(
                        color = Color(0xFFDFF1E9),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Usuario",
                    modifier = Modifier.size(54.dp),
                    tint = VerdePerfil
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Usuario",
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                color = TextoPerfil
            )

            Text(
                text = "usuario@example.com",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(22.dp))

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
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    PerfilOpcion(
                        icono = Icons.Default.Edit,
                        texto = "Editar perfil"
                    )

                    HorizontalDivider(color = Color(0xFFF0F0F0))

                    PerfilOpcion(
                        icono = Icons.Default.Notifications,
                        texto = "Notificaciones"
                    )

                    HorizontalDivider(color = Color(0xFFF0F0F0))

                    PerfilOpcion(
                        icono = Icons.Default.CreditCard,
                        texto = "Mis datos"
                    )

                    HorizontalDivider(color = Color(0xFFF0F0F0))

                    PerfilOpcion(
                        icono = Icons.Default.Settings,
                        texto = "Configuración"
                    )

                    HorizontalDivider(color = Color(0xFFF0F0F0))

                    PerfilOpcion(
                        icono = Icons.Default.HelpOutline,
                        texto = "Ayuda"
                    )

                    HorizontalDivider(color = Color(0xFFF0F0F0))

                    PerfilOpcion(
                        icono = Icons.Default.Logout,
                        texto = "Cerrar sesión"
                    )
                }
            }
        }
    }
}

@Composable
private fun PerfilOpcion(
    icono: androidx.compose.ui.graphics.vector.ImageVector,
    texto: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(57.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icono,
            contentDescription = null,
            tint = TextoPerfil,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(20.dp))

        Text(
            text = texto,
            modifier = Modifier.weight(1f),
            fontSize = 15.sp,
            color = TextoPerfil
        )

        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            tint = Color.Gray
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PerfilScreenPreview() {
    MaterialTheme {
        PerfilScreen(onVolver = {})
    }
}
