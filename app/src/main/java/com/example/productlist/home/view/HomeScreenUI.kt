package com.example.productlist.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.productlist.R
import com.example.productlist.common.view.ProductInfoUI
import com.example.productlist.home.model.Product
import com.example.productlist.home.viewmodel.HomeViewModel
import com.example.productlist.navigation.Screen
import com.example.productlist.util.AppConstants
import com.example.productlist.util.CoilImageBuilder
import kotlinx.coroutines.flow.collect


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenUI(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = { HomeToolBar(navController = navController, homeViewModel) },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                ProductsListView(homeViewModel, navController)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeToolBar(navController: NavController, viewModel: HomeViewModel) {
    TopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Gray,
            titleContentColor = Gray
        ),
        title = {
            Text(
                stringResource(R.string.home),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = White
            )
        },
        actions = {
            IconButton(onClick = { viewModel.fetchProductsList() }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_refresh),
                    contentDescription = stringResource(R.string.carticon)
                )
            }
            IconButton(onClick = { navController.navigate(Screen.CartScreen.route) }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_shopping_cart),
                    contentDescription = stringResource(R.string.carticon)
                )
            }
        }
    )
}

@Composable
fun ProductsListView(homeViewModel: HomeViewModel, navController: NavController) {
    val response = homeViewModel.productsResponse.collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(count = response.itemCount) {
            ProductListItem(response[it], navController)
        }
        response.apply {
            when {
                loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {
                    item {
                        ShowProgress()
                    }
                }

                loadState.refresh is LoadState.Error || loadState.append is LoadState.Error -> {
                    item {
                        ErrorView()
                    }
                }
            }
        }
    }
}

@Composable
fun ShowProgress() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(CenterHorizontally), color = Gray
        )
    }
}

@Composable
fun ErrorView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.unable_to_load_please_try_again),
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                color = Color.Black
            )
        )
    }
}

@Composable
fun ProductListItem(product: Product?, navController: NavController) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    AppConstants.PRODUCT_DETAILS_ARG_KEY, product
                )
                navController.navigate(Screen.DetailScreen.route)
            }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(10.dp)
        ) {
            CoilImageBuilder(
                imgUrl = product?.thumbnail ?: "-",
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .padding(5.dp)
                    .weight(.30f)
            )
            ProductInfoUI(product = product, modifier = Modifier.weight(.30f))
        }
    }
}

