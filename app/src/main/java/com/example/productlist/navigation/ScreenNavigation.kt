package com.example.productlist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.productlist.cart.view.CartScreenUI
import com.example.productlist.home.view.HomeScreenUI
import com.example.productlist.home.model.Product
import com.example.productlist.productDetails.view.ProductDetailsUI
import com.example.productlist.util.AppConstants

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(Screen.HomeScreen.route) {
            HomeScreenUI(navController = navController)
        }

        composable(Screen.DetailScreen.route)
        {
            val productDetails =
                navController.previousBackStackEntry?.savedStateHandle?.get<Product>(
                    AppConstants.PRODUCT_DETAILS_ARG_KEY
                ) ?: Product()

            ProductDetailsUI(product = productDetails, navController = navController)
        }

        composable(Screen.CartScreen.route) {
            CartScreenUI()
        }
    }
}