package com.tagcommander.tcdemo

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tagcommander.tcdemo.ui.theme.Product
import com.tagcommander.tcdemo.ui.theme.ProductCategory

@Composable
fun ProductRow(products: List<Product>, title: String, navController: NavHostController)
{
    val scrollState = rememberScrollState()

    Column() {
        Text(text = title, modifier = Modifier.padding(8.dp), fontSize = 28.sp)

        Row(Modifier.horizontalScroll(scrollState)) {
            products.map{
                ProductItem(product = it, navController)
            }
        }
    }
}

@Preview
@Composable
fun previewProductRow()
{
    val p1 = Product("1", "Street Fighter", "", false, false, 24f, "sf", ProductCategory.VIDEO_GAMES.toString())
    val p2 = Product("2", "God Of War", "", false, false, 69f, "gow", ProductCategory.VIDEO_GAMES.toString())
    val p3 = Product("3", "Fifa", "", false, false, 34f, "fifa", ProductCategory.VIDEO_GAMES.toString())
    val p4 = Product("4", "Spider Man", "", false, false, 49f, "spider", ProductCategory.VIDEO_GAMES.toString())

    ProductRow(listOf(p1,p2,p3,p4), "Games", rememberNavController())
}
