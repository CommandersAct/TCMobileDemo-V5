package com.tagcommander.tcdemo

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.tagcommander.tcdemo.models.CartViewModel
import com.tagcommander.tcdemo.models.NavigationItem
import com.tagcommander.tcdemo.ui.theme.Product

@SuppressLint("UnrememberedMutableState")
@Composable
fun CartScreen(navController: NavController, viewModel: CartViewModel = CartViewModel())
{
    var couponValue by remember { mutableStateOf("") }
    val totalAmount = viewModel.cartProducts.value.filter { it.isInCart }.map{it.price}.sum()
    val discountedAmount = mutableStateOf(totalAmount)
    val isValidCart = mutableStateOf(viewModel.cartProducts.value.isNotEmpty())

    Scaffold(bottomBar = {BottomNavigationBar(navController)})
    { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column {
                Text(text = "Panier", fontSize = 16.sp, modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp), textAlign = TextAlign.Center)
                Divider()
                val scrollState = rememberScrollState()

                Column(
                    Modifier
                        .verticalScroll(scrollState)
                        .weight(0.7f)) {

                    viewModel.cartProducts.value.map{CartRow(product = it, viewModel)}
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Divider()
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp), Arrangement.SpaceBetween) {

                        BasicTextField(
                            value = couponValue,
                            onValueChange = {couponValue = it},
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                            singleLine = true,
                            decorationBox = { innerTextField ->

                                Row(
                                    Modifier
                                        .padding(top = 8.dp)
                                        .padding(start = 24.dp)
                                ) {

                                    if (couponValue.isEmpty()) {
                                        Text("Coupon ...", color = Color.Gray.copy(alpha = 0.4f))
                                    }

                                    innerTextField()
                                }

                            }
                        )

                        Button(onClick =
                        {
                            if (couponValue.isNotEmpty())
                            {
                                discountedAmount.value = (totalAmount * 0.8f)
                            }
                        }, Modifier.padding(end = 12.dp)) {
                            Text(text = "Valider")
                        }
                    }

                    totalAmount(totalAmount, discountedAmount)

                    Button( onClick = { navController.navigateTo(NavigationItem.PaymentScreen.route) },
                            enabled = isValidCart.value, modifier = Modifier.padding(bottom = 8.dp)) {
                        Text(text = "Procéder au paiement")
                    }
                }

            }
        }
    }
}

@Composable
fun totalAmount(totalAmount: Float, discountedAmount: MutableState<Float>)
{
    if (totalAmount != 0f && totalAmount != discountedAmount.value)
    {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp), Arrangement.SpaceBetween) {

            Text(text = "Montant Total : ", fontSize = 26.sp, modifier = Modifier.padding(start = 24.dp))

            Column() {
                Text(text = "${totalAmount} €", style = TextStyle(textDecoration = TextDecoration.LineThrough), fontSize = 26.sp, color = Color.Red, modifier = Modifier.padding(end = 12.dp))

                Text(text = "${discountedAmount.value} €", fontSize = 26.sp, color = Color.Green, modifier = Modifier.padding(end = 12.dp))
            }
        }
    }
    else
    {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(top = 8.dp), Arrangement.SpaceBetween) {

        Text(text = "Montant Total : ", fontSize = 26.sp, modifier = Modifier.padding(start = 24.dp))

        Text(text = "${totalAmount} €", fontSize = 26.sp, color = Color.Green, modifier = Modifier.padding(end = 12.dp))
        }
    }
}

@Composable
fun CartRow(product: Product, viewModel: CartViewModel)
{
    val resId: Int = LocalContext.current.getResources().getIdentifier(product.imageName, "drawable", LocalContext.current.getPackageName())
    val image = painterResource(id = resId)
    val imageModifier = Modifier
        .size(120.dp, 120.dp)
        .fillMaxSize()
        .padding(start = 24.dp)
        .clip(RoundedCornerShape(12.dp))

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        Arrangement.SpaceBetween
    ) {
        Text(text = product.name, fontSize = 32.sp, modifier = Modifier.padding(start = 24.dp))

        Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
            Image(painter = image, contentDescription = "", modifier = imageModifier)

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Price : ${product.price}", Modifier.padding(24.dp), fontWeight = FontWeight.Bold, fontSize = 16.sp)

                Box(modifier = Modifier.padding(bottom = 12.dp))
                {
                    IconButton(
                        onClick = {
                            viewModel.removeProduct(productId = product.id)
                        }) {
                        Icon(
                            Icons.Filled.Delete,
                            "",
                            tint = Color.Red)
                    }
                }
            }
        }
        
        Divider(Modifier.padding(start = 16.dp, end = 16.dp))
    }
}

@Preview
@Composable
fun CartPreview()
{
    CartScreen(rememberNavController())
}