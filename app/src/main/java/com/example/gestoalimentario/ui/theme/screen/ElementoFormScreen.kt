package com.example.gestoalimentario.ui.theme.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gestoalimentario.model.Estado
import com.example.gestoalimentario.model.Receta
import com.example.gestoalimentario.ui.theme.validacion.ElementoFormValidar

@Composable
fun ElementoFormScreen(
    modifier: Modifier = Modifier,
    elementoInicial: Receta? = null,
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
            estadoOptions.indexOf(elementoInicial?.estado).coerceAtLeast(0)
        )
    }

    var error by remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = if (elementoInicial == null)
                "Añadir alimento"
            else
                "Editar alimento",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = {
                nombre = it
                error = ""
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nombre del alimento") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = categoria,
            onValueChange = {
                categoria = it
                error = ""
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Categoría") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = fechaCompra,
            onValueChange = {
                fechaCompra = it
                error = ""
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Fecha de compra (dd/mm/aaaa)") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = fechaCaducidad,
            onValueChange = {
                fechaCaducidad = it
                error = ""
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Fecha de caducidad (dd/mm/aaaa)") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Estado del alimento",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        SingleChoiceSegmentedButtonRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            estadoOptions.forEachIndexed { index, estado ->

                SegmentedButton(
                    selected = estadoIndex == index,
                    onClick = {
                        estadoIndex = index
                    },
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = estadoOptions.size
                    )
                ) {
                    Text(estado)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        if (error.isNotEmpty()) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {

            OutlinedButton(
                onClick = onCancelarClick
            ) {
                Text("Cancelar")
            }

            Spacer(modifier = Modifier.padding(4.dp))

            Button(
                onClick = {

                    val resultado = ElementoFormValidar.validar(
                        nombre,
                        categoria,
                        fechaCompra,
                        fechaCaducidad
                    )

                    if (resultado != null) {

                        error = resultado

                    } else {

                        error = ""

                        // Crear el alimento
                        val nuevoElemento = Receta(
                            id = elementoInicial?.id ?: 0L,
                            nombre = nombre.trim(),
                            categoria = categoria.trim(),
                            fecha_compra = fechaCompra.trim(),
                            fecha_caducado = fechaCaducidad.trim(),
                            estado = estadoOptions[estadoIndex]
                        )

                        onSaveClick(nuevoElemento)
                    }
                },
                shape = RoundedCornerShape(24.dp)
            ) {
                Text("Guardar")
            }
        }
    }
}
