package com.example.productlist.common.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.productlist.home.model.Product

@Composable
fun ProductInfoUI(product: Product?, modifier: Modifier) {
    Column(modifier = modifier) {
        ProductTitle(product)
        ProductPriceDetails(product)
    }
}

@Composable
fun ProductPriceDetails(product: Product?) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$${product?.price.toString() }",
            style = MaterialTheme.typography.bodyLarge.copy(color = Color.Black)
        )
        Text(
            text = " (-${product?.discountPercentage}%".plus(")"),
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.DarkGray)
        )
    }
}

@Composable
fun ProductTitle(product: Product?) {
    Text(
        text = product?.title.toString(),
        modifier = Modifier.padding(6.dp),
        style = MaterialTheme.typography.titleLarge.copy(color = Color.Black)
    )
}

@Composable
fun ProductDesc(product: Product?){
    Text(
        text = product?.description.toString(),
        modifier = Modifier.padding(6.dp),
        style = MaterialTheme.typography.bodyMedium.copy(color = Color.Black)
    )
}