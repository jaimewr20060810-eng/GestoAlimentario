
package com.example.gestoalimentario.ui.theme.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gestoalimentario.model.Estado
import com.example.gestoalimentario.model.Receta

@Composable
fun ElementoFormScreen(
    modifier: Modifier = Modifier,
    elementoInicial: Receta?,
    onSaveClick: (Receta) -> Unit,
    onCancelarClick: () -> Unit
) {

    val estadoOptions = listOf(
        Estado.NO_CADUCADO,
        Estado.CADUCADO
    )

    var nombre by remember {
        mutableStateOf(elementoInicial?.nombre ?: "")
    }

    var categoria by remember {
        mutableStateOf(elementoInicial?.categoria ?: "")
    }

    var fechaCompra by remember {
        mutableStateOf(elementoInicial?.fecha_compra ?: "")
    }

    var fechaCaducidad by remember {
        mutableStateOf(elementoInicial?.fecha_caducado ?: "")
    }

    var estadoIndex by remember {
        mutableIntStateOf(
            estadoOptions.indexOf(elementoInicial?.estado)
                .coerceAtLeast(0)
        )
    }

    var errorNombre by remember {
        mutableStateOf<String?>(null)
    }

    var errorCategoria by remember {
        mutableStateOf<String?>(null)
    }

    var errorFechaCompra by remember {
        mutableStateOf<String?>(null)
    }

    var errorFechaCaducidad by remember {
        mutableStateOf<String?>(null)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Text(
            text = if (elementoInicial == null)
                "Añadir alimento"
            else
                "Editar alimento",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nombre,
            onValueChange = {
                nombre = it
                errorNombre = null
            },
            label = { Text("Nombre del alimento") },
            isError = errorNombre != null,
            supportingText = {
                errorNombre?.let {
                    Text(it, color = MaterialTheme.colorScheme.error)
                }
            },
            singleLine = true
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = categoria,
            onValueChange = {
                categoria = it
                errorCategoria = null
            },
            label = { Text("Categoría") },
            isError = errorCategoria != null,
            supportingText = {
                errorCategoria?.let {
                    Text(it, color = MaterialTheme.colorScheme.error)
                }
            },
            singleLine = true
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = fechaCompra,
            onValueChange = {
                fechaCompra = it
                errorFechaCompra = null
            },
            label = { Text("Fecha de compra (dd/mm/aaaa)") },
            isError = errorFechaCompra != null,
            supportingText = {
                errorFechaCompra?.let {
                    Text(it, color = MaterialTheme.colorScheme.error)
                }
            },
            singleLine = true
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = fechaCaducidad,
            onValueChange = {
                fechaCaducidad = it
                errorFechaCaducidad = null
            },
            label = { Text("Fecha de caducidad (dd/mm/aaaa)") },
            isError = errorFechaCaducidad != null,
            supportingText = {
                errorFechaCaducidad?.let {
                    Text(it, color = MaterialTheme.colorScheme.error)
                }
            },
            singleLine = true
        )

        Text(
            text = "Estado del alimento",
            style = MaterialTheme.typography.titleMedium
        )

        SingleChoiceSegmentedButtonRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            estadoOptions.forEachIndexed { index, label ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = estadoOptions.size
                    ),
                    onClick = {
                        estadoIndex = index
                    },
                    selected = index == estadoIndex,
                    label = {
                        Text(label)
                    }
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                8.dp,
                Alignment.End
            )
        ) {

            TextButton(
                onClick = onCancelarClick
            ) {
                Text("Cancelar")
            }

            Button(
                onClick = {

                    errorNombre = if (nombre.isBlank())
                        "El nombre es obligatorio"
                    else null

                    errorCategoria = if (categoria.isBlank())
                        "La categoría es obligatoria"
                    else null

                    errorFechaCompra = if (fechaCompra.isBlank())
                        "Introduce la fecha de compra"
                    else null

                    errorFechaCaducidad = if (fechaCaducidad.isBlank())
                        "Introduce la fecha de caducidad"
                    else null

                    if (
                        errorNombre == null &&
                        errorCategoria == null &&
                        errorFechaCompra == null &&
                        errorFechaCaducidad == null
                    ) {

                        onSaveClick(
                            Receta(
                                id = elementoInicial?.id ?: 0L,
                                nombre = nombre.trim(),
                                categoria = categoria.trim(),
                                fecha_compra = fechaCompra.trim(),
                                fecha_caducado = fechaCaducidad.trim(),
                                estado = estadoOptions[estadoIndex]
                            )
                        )
                    }
                }
            ) {
                Text("Guardar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ElementoFormScreenPreview() {
    ElementoFormScreen(
        modifier = Modifier.fillMaxSize(),
        elementoInicial = null,
        onSaveClick = {},
        onCancelarClick = {}
    )
}
