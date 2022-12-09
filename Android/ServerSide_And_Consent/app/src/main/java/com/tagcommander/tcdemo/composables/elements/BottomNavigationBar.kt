package com.tagcommander.tcdemo

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.tagcommander.tcdemo.models.NavigationItem


@Composable
fun BottomNavigationBar(navController : NavController)
{
    val items = listOf(
        NavigationItem.Home,
        NavigationItem.Cart,
        NavigationItem.Banner
    )

    BottomNavigation(backgroundColor = Color.White, contentColor = Color.Black)
    {
        items.forEach{
                item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title, modifier = Modifier.size(23.dp)) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = false,
                onClick = {
                    navController.navigateInBottomBar(item.route)
                })
        }
    }
}