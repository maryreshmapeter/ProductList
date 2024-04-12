package com.example.productlist.cart.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.productlist.MainCoroutineRule
import com.example.productlist.database.model.CartItem
import com.example.productlist.repository.CartsRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock

class CartViewModelTest {

    private lateinit var cartViewModel: CartViewModel
    private lateinit var cartRepository: CartsRepository

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRole = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        cartRepository = mock()
        cartViewModel = CartViewModel(cartRepository)
    }

    @Test
    fun getCartItemsTest(): Unit = runBlocking {
        cartViewModel.getCartItems()
        Truth.assertThat(cartViewModel.cartItemsList.value.isNotEmpty())
    }

    @Test
    fun deleteFromCartTest(): Unit = runBlocking {
        val cartItem = CartItem(
            12345,
            "Samsung Galaxy F22",
            "Samsung's new variant",
            899,
            1,
            "Samsung",
            "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg"
        )
        cartViewModel.deleteFromCart(cartItem)
        Truth.assertThat(cartViewModel.deletedItemId.intValue != 0)
    }
}