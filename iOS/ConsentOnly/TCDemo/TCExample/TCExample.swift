//
//  ServerSideExample.swift
//  TCDemo
//
//  Created by Abdelhakim SAID on 31/05/2022.
//

import Foundation
import TCCore
import TCConsent_IAB
import TCIAB

final class TCExample
{
    private static let privacyID:Int32 = 72
    private static let siteID:Int32 = 3311
    private static let sourceKey = "632a2280-880f-4812-a311-4c5e07cf90e9"
    
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
    
}
