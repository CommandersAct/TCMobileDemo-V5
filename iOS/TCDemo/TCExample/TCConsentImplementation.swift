//
//  TCConsentImplementation.swift
//  TCDemo
//
//  Created by Abdelhakim SAID on 31/05/2022.
//

import Foundation
import TCCore
import TCConsent
import SwiftUI

class TCConsentImplementation : NSObject, TCPrivacyCallbacks
{
    let privacyID:Int32 = 22
    let siteID:Int32 = 3311
    static let shared = TCConsentImplementation()
    
    private override init() {
        super.init()
    }
    
    func initTCConsent()
    {
        #if TC_USE_CONSENT
        TCConsentAPI.shouldDisplayPrivacyCenter()
        TCMobileConsent.sharedInstance().callback = self
        TCMobileConsent.sharedInstance().switchDefaultState = true
        TCMobileConsent.sharedInstance().generatePublisherTC = false
        TCMobileConsent.sharedInstance().setSiteID(siteID, andPrivacyID: privacyID)
        TCMobileConsent.sharedInstance().useCustomPublisherRestrictions()
        print("special feature = ", TCConsentAPI.isIABSpecialFeatureAccepted(1));
        print("custom special feature = ", isIABSpecialFeatureAccepted(ID: 1));
        #else
        Toast.show(message: "ServerSide only ! Consent is not available on this App Target")
        #endif
    }
    
    func isIABSpecialFeatureAccepted(ID: Int) -> Bool {
        let offsetID = ID + 13000
        let consent = TCUserDefaults.retrieveInfo(fromUserDefaults: "PRIVACY_FEAT_\(offsetID)")
        return consent == "1"
    }
    
    func firebaseConsentChanged(_ firebaseConsent: [String : NSNumber]!)
    {
        /*
                        Only if you want TCConsent to propagate and map categories onto Firebase's Categories,
                            otherwise, just keep implementation of this callback empty
                                            Check documentation for more info.
         */
        
        TCFirebaseImplementation.firebaseConsentChanged(firebaseConsent)
    }
    
    func acceptAllButtonAction(){
        TCMobileConsent.sharedInstance().acceptAllConsent()
    }
    
    func refuseAllButtonAction(){
        TCMobileConsent.sharedInstance().refuseAllConsent()
    }
    
    func shouldDisplayPrivacyCenter() -> Bool{
        return TCConsentAPI.shouldDisplayPrivacyCenter()
    }
    
    struct PrivacyCenterWrapper : UIViewControllerRepresentable
    {
        func makeUIViewController(context: Context) -> TCPrivacyCenterViewController {
            
            let picker = TCPrivacyCenterViewController()
            return picker
        }
        
        func updateUIViewController(_ uiViewController: TCPrivacyCenterViewController, context: Context) {
        }
        
        
        typealias UIViewControllerType = TCPrivacyCenterViewController

    }
}
