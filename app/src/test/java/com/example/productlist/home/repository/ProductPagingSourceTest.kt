package com.example.productlist.home.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import com.example.productlist.home.model.Product
import com.example.productlist.home.model.ProductsResponse
import com.example.productlist.repository.ProductRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class ProductPagingSourceTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var productRepo: ProductRepository
    private lateinit var pagingSource: ProductsPagingSource

    @Before
    fun setUp() {
        productRepo = mock()
        pagingSource = ProductsPagingSource(productRepo)
    }

    companion object {
        val response: Response<ProductsResponse> = Response.success(ProductsResponse(
            productsList = listOf(
                Product(id = 100)
            ),
            total = 30,
            limit = 10,
            skip = 0
        ))
        val emptyResponse: Response<ProductsResponse> = Response.success(ProductsResponse(
            productsList = emptyList(),
            total = null,
            limit = 0,
            skip = 0
        ))
    }

    @Test
    fun `paging source load - failure - received empty list`() = runTest {
        given(productRepo.getProducts(any(), any())).willReturn(emptyResponse)
        val expectedResult = emptyResponse.body()?.let {
            PagingSource.LoadResult.Page(
                data = it.productsList,
                prevKey = null,
                nextKey = null
            )
        }
        assertEquals(
            expectedResult, pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }

    @Test
    fun `paging source refresh - success`() = runTest {
        given(productRepo.getProducts(any(), any())).willReturn(response)
        val expectedResult = response.body()?.let {
            PagingSource.LoadResult.Page(
                data = it.productsList,
                prevKey = null,
                nextKey = 10
            )
        }
        assertEquals(
            expectedResult, pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = 0,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            )
        )
    }
}