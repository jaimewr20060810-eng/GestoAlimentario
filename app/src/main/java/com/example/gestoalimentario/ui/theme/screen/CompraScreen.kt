package com.example.gestoalimentario.ui.theme.screen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private data class ProductoCompra(
    val id: Long,
    val nombre: String,
    val cantidad: String = "",
    val peso: String = ""
)

@Composable
fun CompraScreen(
    modifier: Modifier = Modifier
) {

    val productos = remember {
        mutableStateListOf<ProductoCompra>()
    }

    val nombreNuevo = remember { mutableStateOf("") }
    val cantidadNueva = remember { mutableStateOf("") }
    val pesoNuevo = remember { mutableStateOf("") }

    val mostrarFormulario = remember { mutableStateOf(false) }
    val error = remember { mutableStateOf("") }

    val productoEditandoId = remember {
        mutableStateOf<Long?>(null)
    }

    val siguienteId = remember {
        mutableStateOf(1L)
    }

    fun abrirFormularioNuevo() {
        nombreNuevo.value = ""
        cantidadNueva.value = ""
        pesoNuevo.value = ""
        productoEditandoId.value = null
        error.value = ""
        mostrarFormulario.value = true
    }

    fun abrirFormularioEdicion(producto: ProductoCompra) {
        nombreNuevo.value = producto.nombre
        cantidadNueva.value = producto.cantidad
        pesoNuevo.value = producto.peso
        productoEditandoId.value = producto.id
        error.value = ""
        mostrarFormulario.value = true
    }

    fun guardarProducto() {

        val nombre = nombreNuevo.value.trim()

        if (nombre.isBlank()) {
            error.value = "El nombre del producto es obligatorio."
            return
        }

        val productoActualizado = ProductoCompra(
            id = productoEditandoId.value ?: siguienteId.value,
            nombre = nombre,
            cantidad = cantidadNueva.value.trim(),
            peso = pesoNuevo.value.trim()
        )

        if (productoEditandoId.value == null) {

            productos.add(productoActualizado)
            siguienteId.value++

        } else {

            val indice = productos.indexOfFirst {
                it.id == productoEditandoId.value
            }

            if (indice != -1) {
                productos[indice] = productoActualizado
            }
        }

        mostrarFormulario.value = false
        error.value = ""
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Lista de la compra",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Organiza lo que necesitas comprar",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(22.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            OutlinedTextField(
                value = nombreNuevo.value,
                onValueChange = {
                    nombreNuevo.value = it
                    error.value = ""
                },
                modifier = Modifier.weight(1f),
                placeholder = {
                    Text("Añadir producto")
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp)
            )

            Button(
                onClick = {
                    abrirFormularioNuevo()
                },
                modifier = Modifier.height(56.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = "+",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }

        if (error.value.isNotEmpty() && !mostrarFormulario.value) {
            Text(
                text = error.value,
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Pendientes: ${productos.size}",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (productos.isEmpty()) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Todavía no tienes productos en tu lista.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

        } else {

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                items(
                    items = productos,
                    key = { producto -> producto.id }
                ) { producto ->

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        )
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 16.dp,
                                    vertical = 14.dp
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {

                                Text(
                                    text = producto.nombre,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontWeight = FontWeight.Medium
                                )

                                if (
                                    producto.cantidad.isNotBlank() ||
                                    producto.peso.isNotBlank()
                                ) {

                                    Spacer(
                                        modifier = Modifier.height(4.dp)
                                    )

                                    val detalles = buildList {
                                        if (producto.cantidad.isNotBlank()) {
                                            add("Cantidad: ${producto.cantidad}")
                                        }

                                        if (producto.peso.isNotBlank()) {
                                            add("Peso: ${producto.peso}")
                                        }
                                    }

                                    Text(
                                        text = detalles.joinToString(" · "),
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }

                            TextButton(
                                onClick = {
                                    abrirFormularioEdicion(producto)
                                }
                            ) {
                                Text("Editar")
                            }

                            TextButton(
                                onClick = {
                                    productos.removeAll {
                                        it.id == producto.id
                                    }
                                }
                            ) {
                                Text(
                                    text = "Quitar",
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if (mostrarFormulario.value) {

        AlertDialog(
            onDismissRequest = {
                mostrarFormulario.value = false
            },

            title = {
                Text(
                    text = if (productoEditandoId.value == null) {
                        "Añadir producto"
                    } else {
                        "Editar producto"
                    }
                )
            },

            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    OutlinedTextField(
                        value = nombreNuevo.value,
                        onValueChange = {
                            nombreNuevo.value = it
                            error.value = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Nombre del producto *")
                        },
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = cantidadNueva.value,
                        onValueChange = {
                            cantidadNueva.value = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Cantidad (opcional)")
                        },
                        placeholder = {
                            Text("Ej. 2 unidades")
                        },
                        singleLine = true
                    )

                    OutlinedTextField(
                        value = pesoNuevo.value,
                        onValueChange = {
                            pesoNuevo.value = it
                        },
                        modifier = Modifier.fillMaxWidth(),
                        label = {
                            Text("Peso (opcional)")
                        },
                        placeholder = {
                            Text("Ej. 500 g o 1 kg")
                        },
                        singleLine = true
                    )

                    if (error.value.isNotEmpty()) {
                        Text(
                            text = error.value,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            },

            confirmButton = {
                Button(
                    onClick = {
                        guardarProducto()
                    }
                ) {
                    Text("Guardar")
                }
            },

            dismissButton = {
                TextButton(
                    onClick = {
                        mostrarFormulario.value = false
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}
