package com.example.productlist.cart.repository

import com.example.productlist.database.ProductsDatabase
import com.example.productlist.database.model.CartItem
import com.example.productlist.repository.CartsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartsRepositoryImpl @Inject constructor(
    private val db: ProductsDatabase,
) : CartsRepository{
    override fun getCartItems(): Flow<List<CartItem>> {
        return db.getCartProductsDao().getAll()
    }

    override suspend fun addCartItem(item: CartItem): Long {
        return db.getCartProductsDao().insert(item)
    }

    override suspend fun deleteCartItem(item: CartItem): Int {
        return db.getCartProductsDao().delete(item)
    }
}
