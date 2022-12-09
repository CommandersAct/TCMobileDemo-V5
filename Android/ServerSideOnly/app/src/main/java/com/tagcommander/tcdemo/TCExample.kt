package com.tagcommander.tcdemo

import android.content.Context
import android.util.Log
import com.tagcommander.lib.core.TCDebug
import com.tagcommander.lib.serverside.TCServerSide
import com.tagcommander.lib.serverside.events.*
import com.tagcommander.lib.serverside.schemas.TCItem
import com.tagcommander.lib.serverside.schemas.TCProduct
import com.tagcommander.tcdemo.models.TCModel
import com.tagcommander.tcdemo.ui.theme.Product

object TCExample
{
    lateinit var tc: TCServerSide
    val siteID = 3311
    val sourceKey = "#YOUR_SOURCE_KEY#"

    fun initTCServerSide(context: Context)
    {
        TCDebug.setDebugLevel(Log.VERBOSE)
        TCDebug.setNotificationLog(true)
        tc = TCServerSide(siteID, sourceKey, context)
    }

    fun sendPageViewEvent(pageName:String, pageType:String)
    {
        val event = TCPageViewEvent(pageType)
        event.pageName = pageName
        tc.execute(event)
    }

    fun sendBeginCheckoutEvent()
    {
        val event = TCBeginCheckoutEvent()
        tc.execute(event)
    }

    fun sendAddToCartEvent(product: Product?)
    {
        val item = TCItem(product?.id, TCProduct(product?.id, product?.name, product?.price), 1)
        val event = TCAddToCartEvent(1.0f,  "euro", listOf(item))
        tc.execute(event)
    }

    fun sendRemoveFromCartEvent(product:Product?)
    {
        val item = TCItem(product?.id, TCProduct(product?.id, product?.name, product?.price), 1)
        val event = TCRemoveFromCartEvent(listOf(item))
        tc.execute(event)
    }

    fun sendAddPaiementInfo(method: String, coupon: String)
    {
        val cartItems = TCModel.products.filter { it.isInCart }.map { TCItem(it.id, TCProduct(it.id, it.name, it.price), 1) }
        val event = TCAddPaymentInfoEvent("card")
        event.coupon = coupon
        event.currency = "EUR"
        event.items = cartItems
        event.addAdditionalParameter("method", method)
        tc.execute(event)
    }

    fun sendAddShippingInfo(adress:String)
    {
        val event = TCAddShippingInfoEvent()
        event.addAdditionalParameter("adress", adress)
        tc.execute(event)
    }

    fun sendPurchaseEvent()
    {
        val event = TCAddShippingInfoEvent()
        tc.execute(event)
    }
}