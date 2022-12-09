package com.tagcommander.tcdemo.ui.theme

import com.fasterxml.jackson.annotation.JsonValue
import com.squareup.moshi.JsonClass

enum class ProductCategory(@JsonValue val typeName: String)
{
    HI_TECH("Hi Tech"), VIDEO_GAMES("Video Games"), GADGETS("Gadgets")
}

@JsonClass(generateAdapter = true)
class Product(var id: String, var name: String, var description: String, var isFavorite: Boolean, var isInCart: Boolean, var price: Float, var imageName: String, var category: String)
