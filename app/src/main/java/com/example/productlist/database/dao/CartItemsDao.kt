package com.example.productlist.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.productlist.database.model.CartItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CartItemsDao {

    @Query("SELECT * FROM products")
    fun getAll(): Flow<List<CartItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: CartItem) : Long

    @Delete
    suspend fun delete(item: CartItem) : Int
}
