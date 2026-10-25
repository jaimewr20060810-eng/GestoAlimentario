package com.example.gestoalimentario.ui.theme.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gestoalimentario.ui.theme.screen.HomeScreen
import com.example.gestoalimentario.ui.theme.screen.DetalleScreen
import com.example.gestoalimentario.ui.theme.screen.CaducidadesScreen
import com.example.gestoalimentario.ui.theme.screen.PerfilScreen

object AppRoutes {
    const val HOME = "home"
    const val DETALLE = "detalle/{id}"
    const val CADUCIDADES = "caducidades"
    const val PERFIL = "perfil"

    fun detalle(id: Int): String = "detalle/$id"
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.HOME
    ) {
        composable(AppRoutes.HOME) {
            HomeScreen(
                onDetalleClick = { id ->
                    navController.navigate(AppRoutes.detalle(id))
                },
                onCaducidadesClick = {
                    navController.navigate(AppRoutes.CADUCIDADES)
                },
                onPerfilClick = {
                    navController.navigate(AppRoutes.PERFIL)
                }
            )
        }

        composable(
            route = AppRoutes.DETALLE,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { entry ->
            val id = entry.arguments?.getInt("id") ?: 0

            DetalleScreen(
                id = id,
                onVolver = {
                    navController.popBackStack()
                }
            )
        }

        composable(AppRoutes.CADUCIDADES) {
            CaducidadesScreen(
                onVolver = {
                    navController.popBackStack()
                }
            )
        }

        composable(AppRoutes.PERFIL) {
            PerfilScreen(
                onVolver = {
                    navController.popBackStack()
                }
            )
        }
    }
}

