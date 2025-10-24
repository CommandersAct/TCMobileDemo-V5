package com.tagcommander.tcdemo.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.tagcommander.tcdemo.ui.theme.Product

class CartViewModel {
    val cartProducts: MutableState<List<Product>> = mutableStateOf(TCModel.products.filter { it.isInCart })

    fun removeProduct(productId: String) {
        TCModel.removeFromCart(productId)
        cartProducts.value = TCModel.products.filter { it.isInCart }
    }
}