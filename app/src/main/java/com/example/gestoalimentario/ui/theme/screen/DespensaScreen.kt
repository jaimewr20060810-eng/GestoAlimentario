package com.example.gestoalimentario.ui.theme.screen
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gestoalimentario.model.Receta
import com.example.gestoalimentario.model.Estado
import com.example.gestoalimentario.ui.theme.components.elementoCard

@Composable
fun DespensaScreen(
    modifier: Modifier = Modifier,
    elementos: List<Receta>,
    onElementoClick: (Long) -> Unit,
    onNuevoElementoClick: () -> Unit
) {
    var busqueda by remember { mutableStateOf("") }
    var filtroSeleccionado by remember { mutableStateOf("Todos") }

    val elementosFiltrados = elementos.filter { elemento ->

        val coincideBusqueda =
            elemento.nombre.contains(busqueda, ignoreCase = true)


        val coincideFiltro = when (filtroSeleccionado) {
            "Disponibles" -> elemento.estado == Estado.NO_CADUCADO
            "Caducados" -> elemento.estado == Estado.CADUCADO
            else -> true
        }

        coincideBusqueda && coincideFiltro
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Mi despensa",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Gestiona todos tus alimentos y controla sus fechas de caducidad.",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = busqueda,
            onValueChange = { busqueda = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Buscar alimento") },
            placeholder = { Text("Nombre o categoría") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Filtrar alimentos",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("Todos", "Disponibles", "Caducados").forEach { filtro ->

                FilterChip(
                    selected = filtroSeleccionado == filtro,
                    onClick = { filtroSeleccionado = filtro },
                    label = { Text(filtro) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Alimentos encontrados: ${elementosFiltrados.size}",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onNuevoElementoClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("＋ Añadir alimento")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (elementosFiltrados.isEmpty()) {

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "No se han encontrado alimentos.",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

        } else {

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(
                    items = elementosFiltrados,
                    key = { elemento -> elemento.id }
                ) { elemento ->

                    elementoCard(
                        modifier = Modifier.clickable {
                            onElementoClick(elemento.id)
                        },
                        elemento = elemento
                    )
                }
            }
        }
    }
}
