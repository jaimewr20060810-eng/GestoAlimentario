package com.example.gestoalimentario.ui.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gestoalimentario.R
import com.example.gestoalimentario.model.Receta
import com.example.gestoalimentario.model.Estado

@Composable
fun elementoCard(
    modifier: Modifier,
    elemento: Receta
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    modifier = Modifier.size(120.dp),
                    painter = painterResource(R.drawable.imagen),
                    contentDescription = "Foto de la comida"
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = elemento.estado,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = elemento.nombre,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "Categoría: ${elemento.categoria}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "Fecha compra: ${elemento.fecha_compra}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "Fecha caducado: ${elemento.fecha_caducado}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun ElementoCardPreview() {
    elementoCard(
        modifier = Modifier.fillMaxWidth(),
        elemento = Receta(
            id=1,
            nombre = "Leche",
            categoria = "Lácteos",
            fecha_compra = "25/09/2026",
            fecha_caducado = "29/09/2026",
            estado = Estado.NO_CADUCADO
        )
    )
}