package com.example.productlist.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.productlist.home.repository.ProductsPagingSource
import com.example.productlist.home.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productsPagingSource: ProductsPagingSource
) : ViewModel() {

    private val _productsResponse: MutableStateFlow<PagingData<Product>> =
        MutableStateFlow(PagingData.empty())

    var productsResponse = _productsResponse.asStateFlow()
        private set

    init {
        fetchProductsList()
    }

    fun fetchProductsList() {
        viewModelScope.launch {
            Pager(
                config = PagingConfig(
                    10, enablePlaceholders = false, prefetchDistance = 2
                )
            ) {
                productsPagingSource
            }.flow.cachedIn(viewModelScope).collect {
                _productsResponse.value = it
            }
        }
    }

}