package com.example.api3.productsProcessor

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ProductListViewTest {
    @Test
    fun `Dado un listado de productos, validar la informacion en pantalla`() {
        val productos = listOf(
            Product(1, "iPhone 13", "The latest iPhone from Apple", 999.99, "USD", true),
            Product(2, "Samsung Galaxy S21", "The latest Samsung phone", 899.99, "USD", true)
        )

        val resultado = obtenerListadoProductos(productos)

        assertEquals(
            """
            iPhone 13 — USD 999.99
            Samsung Galaxy S21 — USD 899.99
            """.trimIndent(),
            resultado
        )
    }

    @Test
    fun `Cuando el listado esta vacio, validar que no se pueda mostrar informacion`() {
        var resultado = obtenerListadoProductos(emptyList())
        if (resultado == "") {
            resultado="No hay productos disponibles"
        }
        assertEquals("No hay productos disponibles", resultado)
    }
}
