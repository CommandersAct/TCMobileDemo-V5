package com.tagcommander.tcdemo.composables.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tagcommander.tcdemo.*
import com.tagcommander.tcdemo.models.NavigationItem
import com.tagcommander.tcdemo.ui.theme.TCDemoTheme

@Composable
fun MainScreen()
{
    val navController = rememberNavController()
    Navigation(navController = navController)
    TCExample.sendPageViewEvent(NavigationItem.Home.title, "view_page")
}

@Composable
fun Navigation(navController: NavHostController)
{
    NavHost(navController, startDestination = NavigationItem.Home.route)
    {
        composable(NavigationItem.Home.route) {
            HomeScreen(navController)
        }

        composable(NavigationItem.Cart.route) {
            CartScreen(navController)
        }

        composable(NavigationItem.Banner.route) {
            Banner(navController)
        }

        composable("${NavigationItem.ProductScreen.route}/{productId}") {
            ProductDetailsScreen(it.arguments?.getString("productId"))
        }

        composable(NavigationItem.PaymentScreen.route) {
            PaymentScreen(navController)
        }

        composable(NavigationItem.ShipmentScreen.route) {
            ShipmentScreen(navController)
        }

        composable(NavigationItem.OrderRecapScreen.route) {
            OrderRecapScreen(navController)
        }

        composable(NavigationItem.OrderValidationScreen.route) {
            OrderValidationScreen(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview()
{
    TCDemoTheme(){
        MainScreen()
    }
}