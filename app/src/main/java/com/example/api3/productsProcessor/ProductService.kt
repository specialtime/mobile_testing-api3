package com.example.api3.productsProcessor

import retrofit2.Call
import retrofit2.http.GET

interface ProductService {
    @GET("b/MX0A")
    fun obtenerProductos(): Call<ProductResponse>
}
