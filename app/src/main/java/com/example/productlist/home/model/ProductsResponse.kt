package com.example.productlist.home.model

import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("products")
    val productsList: List<Product> = emptyList(),
    @SerializedName("total")
    val total: Int? = null,
    @SerializedName("skip")
    val skip: Int,
    @SerializedName("limit")
    val limit: Int
)