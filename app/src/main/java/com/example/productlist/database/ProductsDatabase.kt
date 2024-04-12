package com.example.productlist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.productlist.database.dao.CartItemsDao
import com.example.productlist.database.model.CartItem
import com.example.productlist.util.AppConstants

@Database(entities = [CartItem::class], version = 1, exportSchema = false)
abstract class ProductsDatabase : RoomDatabase() {
    abstract fun getCartProductsDao(): CartItemsDao

    companion object {

        @Volatile
        private var instance: ProductsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context): ProductsDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                ProductsDatabase::class.java,
                AppConstants.DB_NAME
            )
                .build()
        }
    }

}