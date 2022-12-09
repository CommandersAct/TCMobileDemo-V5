//
//  ServerSideExample.swift
//  TCDemo
//
//  Created by Abdelhakim SAID on 31/05/2022.
//

import Foundation
import TCServerSide_noIDFA
import TCCore
import TCConsent_IAB
import TCIAB

final class TCExample
{
    private static let privacyID:Int32 = 72
    private static let siteID:Int32 = 3311
    private static let sourceKey = "${YOUR_SOURCE_KEY}"

    private static var tc:ServerSide? = nil;
    
    static func initTCServerSide()
    {
        TCDebug.setDebugLevel(TCLogLevel_Verbose)
        TCDebug.setNotificationLog(true)
        tc = ServerSide.init(siteID: siteID, andSourceKey: sourceKey, andDefaultBehaviour: PB_DEFAULT_BEHAVIOUR)
    }
    
    static func initTCConsent()
    {
        /**
         * If you need to use your own bundle, please use this for each configuration requiring a specific bundle.
         */
        //  TCConfigurationFileFactory.sharedInstance().setBundle(myBundle, forConfiguration: @"vendor-list")

        TCMobileConsent.sharedInstance().setSiteID(siteID, andPrivacyID: privacyID)
        TCMobileConsent.sharedInstance().setLanguage("fr")
        TCMobileConsent.sharedInstance().generatePublisherTC = true
        TCMobileConsent.sharedInstance().useCustomPublisherRestrictions()
        TCLogger.sharedInstance().logMessage("saved Consent String: \(TCCMPStorage.getConsentString())", with: TCLogLevel_Error)
    }
    
    static func sendPageViewEvent(pageName:String, pageType:String)
    {
        let event = TCPageViewEvent(type: pageType)
        event?.pageName = pageName
        tc?.execute(event)
    }
    
    static func sendBeginCheckoutEvent()
    {
        let event = TCBeginCheckoutEvent()
        tc?.execute(event)
    }
    
    static func sendAddToCartEvent(product:Product)
    {
        let item:TCItem = TCItem(itemId: product.id, with: TCProduct(productId: product.id, withName: product.name, withPrice: NSDecimalNumber(value:product.price)), withQuantity: 1)
        let event = TCAddToCartEvent(value: 1,  withCurrency: "euro", withItems: [item])
        tc?.execute(event)
    }
    
    static func sendAddToWishListEvent(product:Product)
    {
        let item = TCItem(itemId: product.id, with: TCProduct(productId: product.id, withName: product.name, withPrice: NSDecimalNumber(value:product.price)), withQuantity: 1)
        let event = TCAddToWishlistEvent(items: [item!])
        tc?.execute(event)
    }
    
    static func sendRemoveFromCartEvent(product:Product)
    {
        let item = TCItem(itemId: product.id, with: TCProduct(productId: product.id, withName: product.name, withPrice: NSDecimalNumber(value:product.price)), withQuantity: 1)
        let event = TCRemoveFromCartEvent(items: [item as Any])
        tc?.execute(event)
    }
    
    static func sendAddPaiementInfo(method:String, coupon: String, product: Product)
    {
        let item = TCItem(itemId: product.id, with: TCProduct(productId: product.id, withName: product.name, withPrice: NSDecimalNumber(value:product.price)), withQuantity: 1)
        let event = TCAddPaymentInfoEvent(payementMethod: "card")
        event?.coupon = coupon
        event?.currency = "EUR"
        event?.items = [item!]
        tc?.execute(event)
    }
    
    static func sendAddShippingInfo(adress:String)
    {
        let event = TCAddShippingInfoEvent()
        tc?.execute(event)
    }
    
    static func sendPurchaseEvent()
    {
        let event = TCAddShippingInfoEvent()
        tc?.execute(event)
    }
    
    static func getServerSideInstance() -> ServerSide?
    {
        return tc;
    }
}
