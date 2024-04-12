package com.example.productlist.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object DetailScreen : Screen("details_screen")
    object CartScreen : Screen("cart_screen")
}