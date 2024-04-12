package com.example.productlist.cart.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productlist.database.model.CartItem
import com.example.productlist.repository.CartsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartsRepository: CartsRepository
) : ViewModel() {

    private val _cartItemsList = mutableStateOf(emptyList<CartItem>())
    val cartItemsList: MutableState<List<CartItem>> = _cartItemsList

    val deletedItemId = mutableIntStateOf(0)

    init {
        getCartItems()
    }

    fun getCartItems() {
        viewModelScope.launch {
            cartsRepository.getCartItems()
                .flowOn(Dispatchers.IO)
                .collect { items: List<CartItem> ->
                    _cartItemsList.value = items
                }
        }
    }

    fun deleteFromCart(cartItem: CartItem) {
        viewModelScope.launch {
            deletedItemId.value = cartsRepository.deleteCartItem(cartItem)
        }
    }
}