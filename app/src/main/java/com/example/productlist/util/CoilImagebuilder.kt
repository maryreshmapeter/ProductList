package com.example.productlist.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.productlist.R

@Composable
fun CoilImageBuilder(imgUrl: String, modifier: Modifier){
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imgUrl)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.ic_launcher_foreground),
        contentDescription = "",
        contentScale = ContentScale.Inside,
        modifier = modifier
    )
}