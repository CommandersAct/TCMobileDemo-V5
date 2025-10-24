//
//  TCServerSideImplementation.swift
//  TCDemo
//
//  Created by Abdelhakim SAID on 13/10/2025.
//
import TCCore
import TCServerSide_noIDFA
import UserNotifications

struct TCServerSideImplementation {
    private static let siteID: Int32 = 3311
    private static let sourceKey = "632a2280-880f-4812-a311-4c5e07cf90e9"

    private static var tc: ServerSide? = nil

    static func initTCServerSide() {
        #if TC_USE_SERVER_SIDE
        if let firebaseInstance = TCFirebaseImplementation.getInstance() { // only if you use Firebase event forwarding
            tc = ServerSide(
                siteID: siteID,
                andSourceKey: sourceKey,
                andDefaultBehaviour: PB_ALWAYS_ENABLED,
                andFirebaseInstance: firebaseInstance
            )
        } else {
            tc = ServerSide(            // classic initialisation otherwise
                siteID: siteID,
                andSourceKey: sourceKey,
                andDefaultBehaviour: PB_ALWAYS_ENABLED
            )
        }
        #else
        Toast.show(message: "Consent only ! ServerSide is not available on this App Target")
        #endif
    }


    static func sendPageViewEvent(pageName: String, pageType: String) {
        let event = TCPageViewEvent(type: pageType)
        event?.pageName = pageName
        event?.addAdditionalProperty("test_code", withStringValue: "test_code")
        tc?.execute(event)
    }

    static func sendBeginCheckoutEvent() {
        let event = TCBeginCheckoutEvent()
        tc?.execute(event)
    }

    static func sendAddToCartEvent(product: Product) {
        let item: TCItem = TCItem(
            itemId: product.id,
            with: TCProduct(
                productId: product.id,
                withName: product.name,
                withPrice: NSDecimalNumber(value: product.price)
            ),
            withQuantity: 1
        )
        let event = TCAddToCartEvent(
            value: 1,
            withCurrency: "euro",
            withItems: [item]
        )
        tc?.execute(event)
    }

    static func sendAddToWishListEvent(product: Product) {
        let item = TCItem(
            itemId: product.id,
            with: TCProduct(
                productId: product.id,
                withName: product.name,
                withPrice: NSDecimalNumber(value: product.price)
            ),
            withQuantity: 1
        )
        let event = TCAddToWishlistEvent(items: [item!])
        tc?.execute(event)
    }

    static func sendRemoveFromCartEvent(product: Product) {
        let item = TCItem(
            itemId: product.id,
            with: TCProduct(
                productId: product.id,
                withName: product.name,
                withPrice: NSDecimalNumber(value: product.price)
            ),
            withQuantity: 1
        )
        let event = TCRemoveFromCartEvent(items: [item as Any])
        tc?.execute(event)
    }

    static func sendAddPaiementInfo(
        method: String,
        coupon: String,
        product: Product
    ) {
        let item = TCItem(
            itemId: product.id,
            with: TCProduct(
                productId: product.id,
                withName: product.name,
                withPrice: NSDecimalNumber(value: product.price)
            ),
            withQuantity: 1
        )
        let event = TCAddPaymentInfoEvent(paymentMethod: "card")
        event?.coupon = coupon
        event?.currency = "EUR"
        event?.items = [item!]
        tc?.execute(event)
    }

    static func sendAddShippingInfo(adress: String) {
        let event = TCAddShippingInfoEvent()
        tc?.execute(event)
    }

    static func sendPurchaseEvent() {
        let event = TCAddShippingInfoEvent()
        tc?.execute(event)
    }

    static func getServerSideInstance() -> ServerSide? {
        return tc
    }
}
