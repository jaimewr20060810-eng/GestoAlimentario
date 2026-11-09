package com.example.gestoalimentario.ui.theme.navegacion
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.gestoalimentario.R


@Composable
fun MyAppBottomBar(
    navController: NavHostController
) {
    NavigationBar {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        NavigationBarItem(
            selected = currentRoute == Rutas.HOME,
            onClick = {
                navController.navigate(Rutas.HOME) {
                    popUpTo(Rutas.HOME) {
                        inclusive = false
                    }
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.home_icon),
                    contentDescription = "Inicio"
                )
            },
            label = {
                Text("Inicio")
            }
        )

        NavigationBarItem(
            selected = currentRoute == Rutas.DESPENSA,
            onClick = {
                navController.navigate(Rutas.DESPENSA) {
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.list_icon),
                    contentDescription = "Despensa"
                )
            },
            label = {
                Text("Despensa")
            }
        )

        NavigationBarItem(
            selected = currentRoute == Rutas.COMPRA,
            onClick = {
                navController.navigate(Rutas.COMPRA) {
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.shopping_icon),
                    contentDescription = "Compra"
                )
            },
            label = {
                Text("Compra")
            }
        )

        NavigationBarItem(
            selected = currentRoute == Rutas.PERFIL,
            onClick = {
                navController.navigate(Rutas.PERFIL) {
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.perfil_icon),
                    contentDescription = "Perfil"
                )
            },
            label = {
                Text("Perfil")
            }
        )
    }
}

