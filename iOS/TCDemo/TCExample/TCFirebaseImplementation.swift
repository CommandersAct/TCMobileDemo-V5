//
//  TCFirebaseImplementation.swift
//  TCDemo
//
//  Created by Abdelhakim SAID on 13/10/2025.
//
#if TC_USE_FIREBASE
    import FirebaseAnalytics
    import FirebaseCore
#endif

import Foundation

struct TCFirebaseImplementation {
    
    static func initApp()
    {
#if TC_USE_FIREBASE
        FirebaseApp.configure()
#endif
    }
    
    static func firebaseConsentChanged(_ firebaseConsent: [String: NSNumber]!) {
#if TC_USE_FIREBASE
        if let analytics_storage_consent = firebaseConsent[
            "analytics_storage"
        ]?
            .boolValue {
            Analytics.setConsent([
                .analyticsStorage: analytics_storage_consent
                ? .granted : .denied
            ])
        }
        
        if let ad_storage_consent = firebaseConsent["ad_storage"]?.boolValue
        {
            Analytics.setConsent([
                .adStorage: ad_storage_consent ? .granted : .denied
            ])
        }
        
        if let ad_user_data_consent = firebaseConsent["ad_user_data"]?
            .boolValue
        {
            Analytics.setConsent([
                .adUserData: ad_user_data_consent ? .granted : .denied
            ])
        }
        
        if let ad_personalization_consent = firebaseConsent[
            "ad_personalization"
        ]?.boolValue {
            Analytics.setConsent([
                .adPersonalization: ad_personalization_consent
                ? .granted : .denied
            ])
        }
#else
        // No need to do anything otherwise
#endif
    }
    
    static func getInstance() -> Any.Type? {
        #if TC_USE_FIREBASE
        return Analytics.self
        #else
        return nil
        #endif
    }
}
