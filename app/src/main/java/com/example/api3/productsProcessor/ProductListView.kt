package com.example.api3.productsProcessor

import android.content.Context
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class ProductListView(context: Context) : ScrollView(context) {
    private val filas = LinearLayout(context).apply {
        orientation = LinearLayout.VERTICAL
    }

    init {
        addView(filas)
    }

    fun mostrarProductos(products: List<Product>) {
        filas.removeAllViews()
        val padding = (16 * resources.displayMetrics.density).toInt()

        obtenerListadoProductos(products).lineSequence().filter { it.isNotEmpty() }.forEach { fila ->
            filas.addView(TextView(context).apply {
                text = fila
                textSize = 18f
                setPadding(padding, padding, padding, padding)
            })
        }
    }
}

internal fun obtenerListadoProductos(productos: List<Product>): String =
    productos.joinToString("\n") { producto ->
        "${producto.name} — ${producto.currency} ${producto.price}"
    }
