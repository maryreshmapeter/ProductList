package com.example.productlist.productDetails.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.productlist.MainCoroutineRule
import com.example.productlist.home.model.Product
import com.example.productlist.repository.CartsRepository
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock

class ProductDetailsViewModelTest {

    private lateinit var detailViewModel: ProductDetailViewModel
    private lateinit var cartsRepository: CartsRepository

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRole = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        cartsRepository = mock()
        detailViewModel = ProductDetailViewModel(cartsRepository)
    }

    @Test
    fun addItemToCartTest(): Unit = runBlocking {
        val product = Product(
            id = 12345,
            title = "Samsung Galaxy F22",
            description = "Samsung's new variant",
            price = 899,
            discountPercentage = null,
            rating = 4F,
            stock = null,
            brand = "Samsung",
            category = "",
            thumbnail = "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg",
            images = null
        )
        detailViewModel.addItemToCart(product)
        Truth.assertThat(detailViewModel.newItemId.longValue != 0L)
    }
}