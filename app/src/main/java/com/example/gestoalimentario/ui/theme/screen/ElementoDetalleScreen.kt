package com.example.gestoalimentario.ui.theme.screen
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.gestoalimentario.model.Receta

@Composable
fun ElementoDetalleScreen(
    modifier: Modifier = Modifier,
    elemento: Receta,
    onEditarClick: () -> Unit,
    onVolverClick: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Detalle del alimento",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Text(
                    text = elemento.nombre,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Categoría",
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = elemento.categoria,
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "Fecha de compra",
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = elemento.fecha_compra,
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "Fecha de caducidad",
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = elemento.fecha_caducado,
                    style = MaterialTheme.typography.bodyLarge
                )

                Text(
                    text = "Estado",
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = elemento.estado,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = onVolverClick
            ) {
                Text("Volver")
            }

            Button(
                modifier = Modifier.weight(1f),
                onClick = onEditarClick
            ) {
                Text("Editar")
            }
        }
    }
}
