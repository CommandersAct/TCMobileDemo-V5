package com.tagcommander.tcdemo

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tagcommander.tcdemo.models.NavigationItem
import com.tagcommander.tcdemo.ui.theme.Product

@Composable
fun ProductItem(product: Product, navController: NavHostController)
{
        Column(modifier = Modifier
            .padding(4.dp)
            .clickable(onClick = { onProductItemClicked(product, navController) })
        ) {
            val modifier = Modifier
                .size(160.dp, 160.dp)
                .clip(RoundedCornerShape(12.dp))
                .fillMaxSize()

            val resId: Int = LocalContext.current.getResources()
                .getIdentifier(product.imageName, "drawable", LocalContext.current.getPackageName())
            val image = painterResource(id = resId)

            Image(
                contentScale = ContentScale.Crop,
                painter = image,
                contentDescription = "",
                modifier = modifier
            )
            Text(
                text = product.name,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .width(156.dp)
            )
            Text(
                text = "${product.price} €",
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 4.dp)
            )
        }
}

fun onProductItemClicked(product: Product, navController: NavHostController) {
    navController.navigateTo("${NavigationItem.ProductScreen.route}/${product.id}")
}

@Preview
@Composable
fun ProductItemPreview()
{
//    ProductItem(Product("1", "Street Fighter", "", false, false, 24, "speaker", ProductCategory.VIDEO_GAMES))
}