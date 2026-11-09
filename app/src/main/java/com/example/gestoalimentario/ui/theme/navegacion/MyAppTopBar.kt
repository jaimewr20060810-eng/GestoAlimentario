package com.example.gestoalimentario.ui.theme.navegacion
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.gestoalimentario.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppTopBar(
    navController: NavController
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val elementoId = navBackStackEntry?.arguments?.getLong("elementoId")

    TopAppBar(
        title = {
            Text(
                text = when (currentRoute) {
                    Rutas.HOME -> "Gestor Alimentario"
                    Rutas.DESPENSA -> "Mi despensa"
                    Rutas.COMPRA -> "Lista de la compra"
                    Rutas.PERFIL -> "Mi perfil"
                    Rutas.DETALLE -> "Detalle del alimento"
                    Rutas.NUEVO_ELEMENTO -> "Añadir alimento"
                    Rutas.EDITAR_ELEMENTO -> "Editar alimento"
                    else -> ""
                },
                style = MaterialTheme.typography.headlineLarge
            )
        },

        navigationIcon = {
            if (
                currentRoute == Rutas.DETALLE ||
                currentRoute == Rutas.NUEVO_ELEMENTO ||
                currentRoute == Rutas.EDITAR_ELEMENTO
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.arrow_back),
                        contentDescription = "Volver"
                    )
                }
            }
        },

        actions = {
            if (currentRoute == Rutas.DETALLE && elementoId != null) {
                IconButton(
                    onClick = {
                        navController.navigate(
                            Rutas.elementoFormConId(elementoId)
                        )
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.edit_icon),
                        contentDescription = "Editar"
                    )
                }
            }
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )
}

