package com.tagcommander.tcdemo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tagcommander.tcdemo.models.ProductDetailsViewModel
import com.tagcommander.tcdemo.models.TCModel
import com.tagcommander.tcdemo.ui.theme.Product

@Composable
fun ProductDetailsScreen(productId: String?, viewModel: ProductDetailsViewModel = ProductDetailsViewModel())
{
    val product = viewModel.getProductById(productId)
    product?.let {
        val resId: Int = LocalContext.current.getResources().getIdentifier(it.imageName, "drawable", LocalContext.current.getPackageName())
        val scrollState = rememberScrollState()

        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState), horizontalAlignment = Alignment.CenterHorizontally) {
            val imageModifier = Modifier
                .size(280.dp, 280.dp)
                .padding(8.dp)
                .clip(CircleShape)

            Spacer(modifier = Modifier.size(20.dp))

            Box(
                Modifier
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.mint)), contentAlignment = Alignment.Center) {
                Image(painter = painterResource(id = resId), contentDescription = "", modifier = imageModifier)
            }

            Text(text = product.name, fontSize = 34.sp, modifier = Modifier
                .padding(start = 12.dp)
                .fillMaxWidth(), textAlign = TextAlign.Start)
            Text(text = "Prix : ${it.price} €",color = colorResource(id = R.color.teal_200) ,fontSize = 24.sp, modifier = Modifier
                .padding(start = 34.dp)
                .fillMaxWidth(), textAlign = TextAlign.Start)
            Divider(modifier = Modifier.padding(12.dp))

            Row(horizontalArrangement = Arrangement.Start) {
                Text(text = it.description, fontSize = 24.sp, modifier = Modifier
                    .padding(start = 24.dp)
                    .width(300.dp))

                ProductDetailsButtons(product = it, viewModel)
            }
        }
    }
}

@Composable
fun ProductDetailsButtons(product: Product, viewModel: ProductDetailsViewModel)
{
    var productState by remember { mutableStateOf(product.isInCart) }

    IconButton(
            onClick = {
                productState = !productState

                if (productState)
                {
                    viewModel.addToCart(productId = product.id)
                }
                else
                {
                    viewModel.removeFromCart(product.id)
                }
            }) {
            Icon(
                Icons.Filled.ShoppingCart,
                "",
                modifier = Modifier.size(60.dp),
                tint = if (productState) Color.Green else Color.LightGray)
        }
}

@Preview
@Composable
fun ProductDetailsPreview()
{
//    val p1 = Product("1", "Street Fighter", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", false, false, 24, "sf", ProductCategory.VIDEO_GAMES)
//    ProductDetailsButtons(product = p1)
    TCModel.loadProducts(LocalContext.current)
    ProductDetailsScreen(productId = "3")
}
