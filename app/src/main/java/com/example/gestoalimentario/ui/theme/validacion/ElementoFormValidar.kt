package com.example.gestoalimentario.ui.theme.validacion

import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.Locale

object ElementoFormValidar {
    private fun convertirFecha(fecha: String): java.util.Date? {

        val formato = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        formato.isLenient = false

        val posicion = ParsePosition(0)
        val resultado = formato.parse(fecha, posicion)

        return if (
            resultado != null &&
            posicion.index == fecha.length &&
            posicion.errorIndex == -1
        ) {
            resultado
        } else {
            null
        }
    }
    fun validar(
        nombre: String,
        categoria: String,
        fechaCompra: String,
        fechaCaducidad: String
    ): String? {

        if (nombre.isBlank()) {
            return "Introduce el nombre del alimento."
        }

        if (categoria.isBlank()) {
            return "Introduce la categoría."
        }

        if (fechaCompra.isBlank()) {
            return "Introduce la fecha de compra."
        }

        if (fechaCaducidad.isBlank()) {
            return "Introduce la fecha de caducidad."
        }

        val compra = convertirFecha(fechaCompra)

        if (compra == null) {
            return "La fecha de compra no es válida. Usa dd/MM/aaaa."
        }

        val caducidad = convertirFecha(fechaCaducidad)

        if (caducidad == null) {
            return "La fecha de caducidad no es válida. Usa dd/MM/aaaa."
        }

        if (caducidad.before(compra)) {
            return "La fecha de caducidad no puede ser anterior a la compra."
        }

        return null
    }
}
