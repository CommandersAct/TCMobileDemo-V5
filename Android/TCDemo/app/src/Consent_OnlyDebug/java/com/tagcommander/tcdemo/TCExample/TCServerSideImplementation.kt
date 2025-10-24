package com.tagcommander.tcdemo.TCExample

import android.content.Context

import com.tagcommander.tcdemo.ui.theme.Product

object TCServerSideImplementation {


    fun initTCServerSide(context: Context)
    {
        // not used on this variant
    }

    fun sendPageViewEvent(pageName:String, pageType:String)
    {
        // not used on this variant
    }

    fun sendBeginCheckoutEvent()
    {
        // not used on this variant
    }

    fun sendAddToCartEvent(product: Product?)
    {
        // not used on this variant
    }

    fun sendRemoveFromCartEvent(product: Product?)
    {
        // not used on this variant
    }

    fun sendAddPaiementInfo(method: String, coupon: String)
    {
        // not used on this variant
    }

    fun sendAddShippingInfo(adress:String)
    {
        // not used on this variant
    }

    fun sendPurchaseEvent()
    {
        // not used on this variant
    }
}