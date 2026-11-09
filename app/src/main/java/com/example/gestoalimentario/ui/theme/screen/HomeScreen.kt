package com.example.gestoalimentario.ui.theme.screen
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gestoalimentario.model.Receta
import com.example.gestoalimentario.model.Estado
import com.example.gestoalimentario.ui.theme.components.elementoCard

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    elementos: List<Receta>,
    onElementoClick: (Long) -> Unit,
    onVerDespensaClick: () -> Unit,
    onVerCompraClick: () -> Unit,
    onNuevoElementoClick: () -> Unit
) {
    val totalElementos = elementos.size

    val elementosCaducados = elementos.count {
        it.estado == Estado.CADUCADO
    }

    val elementosDisponibles = elementos.count {
        it.estado == Estado.NO_CADUCADO
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "¡Bienvenido a Gestor Alimentario!",
            style = MaterialTheme.typography.headlineSmall
        )


        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Resumen de alimentos",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Card(modifier = Modifier.weight(1f)) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text("Total")
                    Text(
                        text = totalElementos.toString(),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }

            Card(modifier = Modifier.weight(1f)) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text("Disponibles")
                    Text(
                        text = elementosDisponibles.toString(),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }

            Card(modifier = Modifier.weight(1f)) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text("Caducados")
                    Text(
                        text = elementosCaducados.toString(),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Accesos rápidos",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = onNuevoElementoClick,
                modifier = Modifier.weight(1f)
            ) {
                Text("Añadir alimento")
            }

            OutlinedButton(
                onClick = onVerCompraClick,
                modifier = Modifier.weight(1f)
            ) {
                Text("Lista de compra")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = onVerDespensaClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver toda la despensa")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Tus alimentos",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(elementos) { elemento ->
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
