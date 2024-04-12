package com.example.productlist.core.network

import com.example.productlist.home.model.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    suspend fun getProductsList(
        @Query("skip") page: Int,
        @Query("limit") limit: Int
    ): Response<ProductsResponse>
}