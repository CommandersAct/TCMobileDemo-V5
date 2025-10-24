package com.tagcommander.tcdemo.TCExample

import android.content.Context
import android.provider.Settings
import android.util.Log
import com.tagcommander.lib.core.TCDebug
import com.tagcommander.lib.serverside.TCServerSide
import com.tagcommander.lib.serverside.events.TCAddPaymentInfoEvent
import com.tagcommander.lib.serverside.events.TCAddShippingInfoEvent
import com.tagcommander.lib.serverside.events.TCAddToCartEvent
import com.tagcommander.lib.serverside.events.TCBeginCheckoutEvent
import com.tagcommander.lib.serverside.events.TCCustomEvent
import com.tagcommander.lib.serverside.events.TCRemoveFromCartEvent
import com.tagcommander.lib.serverside.schemas.TCItem
import com.tagcommander.lib.serverside.schemas.TCProduct
import com.tagcommander.tcdemo.models.TCModel
import com.tagcommander.tcdemo.ui.theme.Product
import com.tagcommander.tcdemo.BuildConfig

object TCServerSideImplementation {

    val siteID = 4183
    val sourceKey = "939324f7-28ec-4352-9241-ed4ca8405b07"
    lateinit var tc: TCServerSide
    private lateinit var appContext: Context

    fun initTCServerSide(context: Context)
    {
        tc = TCServerSide(siteID, sourceKey, context)
        appContext = context
    }

    fun sendPageViewEvent(pageName:String, pageType:String)
    {
        val event = TCCustomEvent("start")
        event.addAdditionalProperty("custom_page_key", pageName)
        event.addAdditionalProperty("custom_page_type", pageType)
        event.addAdditionalProperty("auto_timezone", Settings.Global.getInt(appContext.getContentResolver(), Settings.Global.AUTO_TIME_ZONE))
        event.addAdditionalProperty("auto_time", Settings.Global.getInt(appContext.getContentResolver(), Settings.Global.AUTO_TIME))
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

    fun sendRemoveFromCartEvent(product: Product?)
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
        event.addAdditionalProperty("method", method)
        tc.execute(event)
    }

    fun sendAddShippingInfo(adress:String)
    {
        val event = TCAddShippingInfoEvent()
        event.addAdditionalProperty("adress", adress)
        tc.execute(event)
    }

    fun sendPurchaseEvent()
    {
        val event = TCAddShippingInfoEvent()
        tc.execute(event)
    }
}