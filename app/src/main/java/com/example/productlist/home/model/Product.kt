package com.example.productlist.home.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    @SerializedName("id")
    val id:Int? = null,
    
    @SerializedName("title")
    val title:String? = null,
    
    @SerializedName("description")
    val description:String? = null,
    
    @SerializedName("price")
    val price:Long? = null,
    
    @SerializedName("discountPercentage")
    val discountPercentage:Double? = null,
    
    @SerializedName("rating")
    val rating:Float? = null,
    
    @SerializedName("stock")
    val stock:Int? = null,
    
    @SerializedName("brand")
    val brand:String? = null,
    
    @SerializedName("category")
    val category:String? = null,
    
    @SerializedName("thumbnail")
    val thumbnail:String? = null,
    
    @SerializedName("images")
    val images:List<String>? = emptyList(),
) : Parcelable