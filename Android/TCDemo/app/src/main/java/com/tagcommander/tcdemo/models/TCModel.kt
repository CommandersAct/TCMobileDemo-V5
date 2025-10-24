package com.tagcommander.tcdemo.models

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tagcommander.tcdemo.R
import com.tagcommander.tcdemo.TCExample.TCServerSideImplementation
import com.tagcommander.tcdemo.ui.theme.Product
import java.io.IOException

object TCModel
{
    lateinit var products: List<Product>

    fun loadProducts(context: Context) {
        lateinit var jsonString: String

        try {
            jsonString = context.resources.openRawResource(R.raw.products)
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            ioException.message?.let { Log.e("", it) }
        }

        val listCountryType = object : TypeToken<List<Product>>() {}.type
        val parsedProducts: List<Product> = Gson().fromJson(jsonString, listCountryType)

        products = parsedProducts
    }

    fun getProductById(productId: String?): Product? {
        return products.find { it.id == productId }
    }

    fun removeFromCart(productId: String) {
        products.find { it.id == productId }?.let {
            it.isInCart = false
        }
    }

    fun addToCart(productId: String) {
        products.find { it.id == productId }?.let {
            it.isInCart = true
        }
    }

    fun resetAll(current: Context) {
        TCServerSideImplementation.sendPurchaseEvent()
        loadProducts(current)
    }
}