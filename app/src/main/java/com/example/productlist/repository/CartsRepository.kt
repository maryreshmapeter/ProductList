package com.example.productlist.repository

import com.example.productlist.database.model.CartItem
import kotlinx.coroutines.flow.Flow

interface CartsRepository {
    fun getCartItems(): Flow<List<CartItem>>
    suspend fun addCartItem(item: CartItem) : Long
    suspend fun deleteCartItem(item: CartItem) : Int
}