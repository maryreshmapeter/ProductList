package com.example.productlist.productDetails.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.productlist.R
import com.example.productlist.common.view.ProductDesc
import com.example.productlist.common.view.ProductPriceDetails
import com.example.productlist.common.view.ProductTitle
import com.example.productlist.home.model.Product
import com.example.productlist.navigation.Screen
import com.example.productlist.productDetails.viewmodel.ProductDetailViewModel
import com.example.productlist.util.CoilImageBuilder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsUI(
    product: Product,
    navController: NavController,
    detailViewModel: ProductDetailViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = ({ DetailToolBar(product = product, navController = navController) }),
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                DetailView(product, detailViewModel, navController)
            }
        }
    )
}

@Composable
fun DetailView(
    product: Product,
    detailViewModel: ProductDetailViewModel,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        ProductTitle(product = product)
        ProductDesc(product = product)
        ProductImagesCarousal(product)
        ProductPriceDetails(product = product)
        ProductRating(product)
        AddToCartButton(detailViewModel, product, navController)
    }
}

@Composable
private fun ProductImagesCarousal(product: Product) {
    val listState = rememberLazyListState()
    val imgList = product.images ?: emptyList()
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
        LazyRow(state = listState) {
            itemsIndexed(imgList) { _, item ->
                ElevatedCard(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 6.dp
                    ),
                    modifier = Modifier.padding(10.dp)
                ) {
                    CoilImageBuilder(
                        imgUrl = item, modifier = Modifier
                            .height(250.dp)
                            .width(250.dp)
                    )
                }

            }
        }
    }
}

@Composable
private fun AddToCartButton(
    detailViewModel: ProductDetailViewModel,
    product: Product,
    navController: NavController
) {
    val context = LocalContext.current
    OutlinedButton(
        onClick = {
            onButtonClicked(detailViewModel, product, context, navController)
        },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(56.dp)
            .padding(top = 30.dp),
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
        border = BorderStroke(
            2.dp, Black
        )
    ) {
        Text(
            text = if (detailViewModel.itemAdded.value) stringResource(R.string.go_to_cart) else stringResource(
                R.string.add_to_cart
            ),
            modifier = Modifier
                .padding(
                    top = 4.dp,
                    bottom = 4.dp
                ),
            style = MaterialTheme.typography.bodySmall.copy(color = White),
        )
        Image(
            painter = if (detailViewModel.itemAdded.value)
                painterResource(id = R.drawable.ic_cart_added)
            else painterResource(id = R.drawable.baseline_add_shopping_cart_24),
            contentDescription = stringResource(
                R.string.add_to_cart
            ),
            modifier = Modifier
                .padding(start = 8.dp)
        )
    }
}

private fun onButtonClicked(
    detailViewModel: ProductDetailViewModel,
    product: Product,
    context: Context,
    navController: NavController
) {
    if (!detailViewModel.itemAdded.value) {
        detailViewModel.addItemToCart(product)
        Toast.makeText(context, context.getString(R.string.item_added_to_cart), Toast.LENGTH_SHORT)
            .show()
    } else {
        navController.navigate(Screen.CartScreen.route)
    }
}

@Composable
private fun ProductRating(product: Product) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.rating, product.rating.toString()),
            modifier = Modifier.padding(6.dp),
            style = MaterialTheme.typography.labelMedium.copy(color = Black)
        )
        Image(painter = painterResource(id = R.drawable.ic_rating_star), contentDescription = "")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailToolBar(product: Product, navController: NavController) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Gray,
            titleContentColor = Color.Gray
        ),
        title = {
            Text(
                product.title.toString(),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
            )
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = stringResource(R.string.backicon),
                )
            }
        }
    )
}
