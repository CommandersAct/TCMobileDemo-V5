package com.tagcommander.tcdemo.models

import com.tagcommander.tcdemo.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String)
{
    object Home: NavigationItem(route = "home", R.drawable.home, "home")
    object Cart: NavigationItem(route = "cart", R.drawable.cart, "cart")
    object Banner: NavigationItem(route = "banner", R.drawable.lock, "banner")
    object ProductScreen: NavigationItem(route = "product_details", 0, "product_details")
    object PaymentScreen: NavigationItem(route = "payment", 0, "payment")
    object ShipmentScreen: NavigationItem(route = "shipment", 0, "shipment")
    object OrderRecapScreen: NavigationItem(route = "recap", 0, "recap")
    object OrderValidationScreen: NavigationItem(route = "validation", 0, "validation")
}
