package com.example.gestoalimentario.ui.theme.navegacion

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.gestoalimentario.model.Receta
import com.example.gestoalimentario.model.Estado
import com.example.gestoalimentario.ui.theme.screen.HomeScreen
import com.example.gestoalimentario.ui.theme.screen.DespensaScreen
import com.example.gestoalimentario.ui.theme.screen.CompraScreen
import com.example.gestoalimentario.ui.theme.screen.PerfilScreen
import com.example.gestoalimentario.ui.theme.screen.ElementoFormScreen
import com.example.gestoalimentario.ui.theme.screen.ElementoDetalleScreen

object Rutas {

    const val HOME = "home"
    const val DESPENSA = "despensa"
    const val COMPRA = "compra"
    const val PERFIL = "perfil"

    const val DETALLE = "detalle/{elementoId}"
    const val NUEVO_ELEMENTO = "nuevo_elemento"
    const val EDITAR_ELEMENTO = "editar_elemento/{elementoId}"

    fun detalleConId(elementoId: Long) =
        "detalle/$elementoId"

    fun elementoFormConId(elementoId: Long) =
        "editar_elemento/$elementoId"
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    val listaElementos = remember {
        mutableStateListOf(

            Receta(
                id = 1,
                nombre = "Leche",
                categoria = "Lácteos",
                fecha_compra = "25/09/2026",
                fecha_caducado = "29/09/2026",
                estado = Estado.NO_CADUCADO
            ),

            Receta(
                id = 2,
                nombre = "Tomate",
                categoria = "Verdura",
                fecha_compra = "26/09/2026",
                fecha_caducado = "30/09/2026",
                estado = Estado.NO_CADUCADO
            ),

            Receta(
                id = 3,
                nombre = "Arroz",
                categoria = "Cereales",
                fecha_compra = "20/09/2026",
                fecha_caducado = "20/09/2027",
                estado = Estado.NO_CADUCADO
            )
        )
    }

    NavHost(
        navController = navController,
        startDestination = Rutas.HOME,
        modifier = modifier
    ) {

        composable(Rutas.HOME) {
            HomeScreen(
                modifier = Modifier,
                elementos = listaElementos,

                onElementoClick = { elementoId ->
                    navController.navigate(
                        Rutas.detalleConId(elementoId)
                    )
                },

                onVerDespensaClick = {
                    navController.navigate(Rutas.DESPENSA)
                },

                onVerCompraClick = {
                    navController.navigate(Rutas.COMPRA)
                },

                onNuevoElementoClick = {
                    navController.navigate(Rutas.NUEVO_ELEMENTO)
                }
            )
        }

        composable(Rutas.DESPENSA) {

            DespensaScreen(
                modifier = Modifier,
                elementos = listaElementos,
                onElementoClick = {
                    navController.navigate(
                        Rutas.detalleConId(it)
                    )
                },
                onNuevoElementoClick = {
                    navController.navigate(
                        Rutas.NUEVO_ELEMENTO
                    )
                }
            )
        }

        composable(Rutas.COMPRA) {

            CompraScreen(
                modifier = Modifier
            )
        }

        composable(Rutas.PERFIL) {

            PerfilScreen(
                modifier = Modifier
            )
        }

        composable(
            route = Rutas.DETALLE,
            arguments = listOf(
                navArgument("elementoId") {
                    type = NavType.LongType
                }
            )
        ) { arg ->

            val elementoId =
                arg.arguments?.getLong("elementoId")

            val elemento = listaElementos.find {
                it.id == elementoId
            }

            if (elemento != null) {

                ElementoDetalleScreen(
                    modifier = Modifier,
                    elemento = elemento,
                    onEditarClick = {
                        navController.navigate(
                            Rutas.elementoFormConId(elemento.id)
                        )
                    },
                    onVolverClick = {
                        navController.popBackStack()
                    }
                )
            }
        }

        composable(Rutas.NUEVO_ELEMENTO) {

            ElementoFormScreen(
                modifier = Modifier,
                elementoInicial = null,

                onSaveClick = { nuevoElemento ->

                    val siguienteId =
                        (listaElementos.maxOfOrNull { it.id } ?: 0L) + 1L

                    listaElementos.add(
                        nuevoElemento.copy(id = siguienteId)
                    )

                    navController.popBackStack()
                },

                onCancelarClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = Rutas.EDITAR_ELEMENTO,
            arguments = listOf(
                navArgument("elementoId") {
                    type = NavType.LongType
                }
            )
        ) { arg ->

            val elementoId =
                arg.arguments?.getLong("elementoId")

            val elementoActual = listaElementos.find {
                it.id == elementoId
            }

            if (elementoActual != null) {

                ElementoFormScreen(
                    modifier = Modifier,
                    elementoInicial = elementoActual,

                    onSaveClick = { elementoActualizado ->

                        val indice = listaElementos.indexOfFirst {
                            it.id == elementoActualizado.id
                        }

                        if (indice != -1) {
                            listaElementos[indice] =
                                elementoActualizado
                        }

                        navController.popBackStack()
                    },

                    onCancelarClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

