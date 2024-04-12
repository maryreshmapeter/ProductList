package com.example.productlist.home.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.productlist.home.model.Product
import com.example.productlist.repository.ProductRepository
import com.example.productlist.util.AppConstants.Companion.LOAD_LIMIT
import com.example.productlist.util.AppConstants.Companion.START_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProductsPagingSource @Inject constructor(
    private val repository: ProductRepository
) : PagingSource<Int, Product>() {

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? = state.anchorPosition?.let {
        state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val page = params.key ?: START_PAGE_INDEX
            val response = repository.getProducts(page, params.loadSize)
            val productsResponse = response.body()
            val productsList = productsResponse?.productsList ?: emptyList()
            try {
                LoadResult.Page(
                    data = productsList,
                    prevKey = if (page == START_PAGE_INDEX) null else page.minus(LOAD_LIMIT),
                    nextKey = if (productsList.isEmpty()) null else page.plus(LOAD_LIMIT)
                )
            } catch (e: IOException) {
                LoadResult.Error(
                    e
                )
            } catch (e: HttpException) {
                LoadResult.Error(
                    e
                )
            }
        } catch (e: Exception) {
            return LoadResult.Error(
                e
            )
        }
    }
}