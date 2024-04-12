package com.example.productlist.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import androidx.paging.filter
import com.example.productlist.MainCoroutineRule
import com.example.productlist.home.model.Product
import com.example.productlist.home.repository.ProductsPagingSource
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class HomeViewModelTest {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var pagingSource: ProductsPagingSource

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRole = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        pagingSource = mock()
        homeViewModel = HomeViewModel(pagingSource)
    }

    @Test
    fun fetchProductsListTestSuccess(): Unit = runBlocking {
        whenever(pagingSource.load(any())).thenReturn(
            PagingSource.LoadResult.Page(
                data = listOf(
                    Product(id = 100)
                ),
                prevKey = null,
                nextKey = null
            )
        )
        homeViewModel.fetchProductsList()

        assertThat(homeViewModel.productsResponse.value.filter { it.id == 100 }).isNotNull()
    }
}