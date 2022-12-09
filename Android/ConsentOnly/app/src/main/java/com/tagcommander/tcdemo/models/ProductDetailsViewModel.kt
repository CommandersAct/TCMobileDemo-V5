package com.tagcommander.tcdemo.models

import com.tagcommander.tcdemo.TCExample
import com.tagcommander.tcdemo.ui.theme.Product

class ProductDetailsViewModel {

    fun getProductById(productId: String?) : Product? {
        return TCModel.getProductById(productId = productId)
    }

    fun addToCart(productId: String) {
        TCModel.addToCart(productId)
    }

    fun removeFromCart(productId: String) {
        TCModel.removeFromCart(productId)
    }
}
