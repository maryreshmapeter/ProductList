package com.example.productlist.cart.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.productlist.R
import com.example.productlist.cart.viewmodel.CartViewModel
import com.example.productlist.database.model.CartItem
import com.example.productlist.util.CoilImageBuilder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreenUI(
) {
    val cartViewModel: CartViewModel = hiltViewModel()
    val cartItems = cartViewModel.cartItemsList.value
    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                CartItems(cartItems, cartViewModel)
            }
        },
        topBar = ({ CartToolBar() })
    )

}

@Composable
fun CartItems(cartItems: List<CartItem>, viewModel: CartViewModel) {
    if (cartItems.isEmpty()) {
        EmptyCartView()
    } else {
        LazyColumn(modifier = Modifier.heightIn(0.dp, (cartItems.size * 200).dp)) {
            itemsIndexed(cartItems) { _, item ->
                CartListItem(item, viewModel = viewModel)
            }
        }
    }

}

@Composable
fun EmptyCartView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(R.string.you_have_no_items_in_the_cart),
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                color = Color.Black
            )
        )
    }
}

@Composable
fun CartListItem(item: CartItem, viewModel: CartViewModel) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier.padding(10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(20.dp)
        ) {
            CoilImageBuilder(
                imgUrl = item.thumbnail ?: "-", modifier = Modifier
                    .padding(10.dp)
                    .weight(.30f)
            )
            CartItemInfo(item = item, modifier = Modifier.weight(.70f))
            DeleteButton(viewModel = viewModel, item = item)
        }
    }
}

@Composable
fun DeleteButton(viewModel: CartViewModel, item: CartItem) {
    val context = LocalContext.current
    Image(
        painter = painterResource(id = R.drawable.ic_delete),
        contentDescription = "",
        modifier = Modifier.clickable {
            deleteItemFromCart(viewModel, context, item)
        })
}

@Composable
fun CartItemInfo(item: CartItem, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = item.title.toString(),
            modifier = Modifier.padding(6.dp),
            style = MaterialTheme.typography.titleLarge.copy(color = Color.Black)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = "$${item.price.toString()}",
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
            )
        }
    }
}

fun deleteItemFromCart(viewModel: CartViewModel, context: Context, item: CartItem) {
    viewModel.deleteFromCart(item)
    Toast.makeText(context, context.getString(R.string.item_removed_from_cart), Toast.LENGTH_SHORT)
        .show()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartToolBar() {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(stringResource(R.string.cart), color = Color.White)
            }
        },
        modifier = Modifier
            .semantics { heading() },
        colors = TopAppBarDefaults.smallTopAppBarColors(Color.Gray),
    )
}
