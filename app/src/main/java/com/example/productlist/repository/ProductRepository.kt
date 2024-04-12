package com.example.productlist.repository

import com.example.productlist.home.model.ProductsResponse
import com.example.productlist.core.network.ApiService
import retrofit2.Response
import javax.inject.Inject


class ProductRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getProducts(
        skip: Int,
        limit: Int
    ): Response<ProductsResponse> =
        apiService.getProductsList(
        skip, limit
    )

}