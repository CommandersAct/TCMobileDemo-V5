package com.tagcommander.tcdemo

import androidx.navigation.NavController
import com.tagcommander.tcdemo.models.NavigationItem

fun NavController.navigateInBottomBar(route:String)
{
    this.navigate(route){
        this@navigateInBottomBar.graph.startDestinationRoute?.let { route ->
            popUpTo(route)
            {
                if(route != graph.startDestinationRoute)
                {
                    inclusive = true
                }
                saveState = true
            }
        }

        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }
}

fun NavController.navigateTo(route:String)
{
    this.navigate(route){
        launchSingleTop = true
        restoreState = true
    }
}

fun NavController.navigateAndPopHome(route: String, homeRoute: String)
{
    this.navigate(route) {
        popUpTo(homeRoute) {
            inclusive = false
        }
    }
}
