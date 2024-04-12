package com.example.productlist.productDetails.viewmodel

import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productlist.database.model.CartItem
import com.example.productlist.home.model.Product
import com.example.productlist.repository.CartsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val cartsRepository: CartsRepository
) : ViewModel() {

    val newItemId = mutableLongStateOf(0)
    val itemAdded = mutableStateOf(false)
    fun addItemToCart(item: Product){
        viewModelScope.launch {
            newItemId.longValue = cartsRepository.addCartItem(CartItem(
                id = item.id?:0,
                title = item.title,
                description = item.description,
                thumbnail = item.thumbnail,
                price = item.price
            ))
            if(newItemId.longValue!=0L) itemAdded.value = true
        }
    }
}