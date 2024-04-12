package com.example.productlist.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class CartItem(
    @PrimaryKey
    val id:Int,
    @ColumnInfo
    val title:String? = null,
    @ColumnInfo
    val description:String? = null,
    @ColumnInfo
    val price:Long? = null,
    @ColumnInfo
    val stock:Int? = null,
    @ColumnInfo
    val brand:String? = null,
    @ColumnInfo
    val thumbnail:String? = null
)
