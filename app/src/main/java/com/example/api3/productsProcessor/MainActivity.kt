package com.example.api3.productsProcessor

import android.os.Bundle
import android.util.Log
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.ComponentActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    private lateinit var listado: ProductListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listado = ProductListView(this)
        setContentView(listado)
        ViewCompat.setOnApplyWindowInsetsListener(listado) { view, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }
        obtenerProductos()
    }

    private fun obtenerProductos() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonkeeper.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ProductService::class.java)
        val call =  service.obtenerProductos()
        call.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                if (response.isSuccessful) {
                    val products = response.body()?.products.orEmpty()
                    listado.mostrarProductos(products)
                    products.forEach { product ->
                        Log.d("Main Activity","Product: ${product.name}")
                    }
                } else {
                    Log.e("Main Activity", "API Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Log.e("Main Activity", "Error al obtener productos: ${t.message}")
            }
        })
    }
}
