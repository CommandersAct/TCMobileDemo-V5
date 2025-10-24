package com.tagcommander.tcdemo.models

import com.tagcommander.tcdemo.TCExample.TCServerSideImplementation
import com.tagcommander.tcdemo.ui.theme.Product

class ProductDetailsViewModel {

    fun getProductById(productId: String?) : Product? {
        return TCModel.getProductById(productId = productId)
    }

    fun addToCart(productId: String) {
        TCModel.addToCart(productId)
        TCServerSideImplementation.sendAddToCartEvent(TCModel.getProductById(productId))
    }

    fun removeFromCart(productId: String) {
        TCModel.removeFromCart(productId)
        TCServerSideImplementation.sendRemoveFromCartEvent(TCModel.getProductById(productId))
    }
}
