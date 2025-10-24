//
//  TCDemoApp.swift
//  TCDemo
//
//  Created by Abdelhakim SAID on 23/05/2022.
//

import SwiftUI
import TCCore

class AppDelegate: NSObject, UIApplicationDelegate {
  func application(_ application: UIApplication,
                   didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
#if TC_USE_FIREBASE
      TCFirebaseImplementation.initApp()
#endif

    return true
  }
}

@main
struct TCDemoApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate
    @StateObject var modelData = ModelData()

    init()
    {
        initCommandersActLibraries()
    }
    
    func initCommandersActLibraries()
    {
        TCDebug.setDebugLevel(TCLogLevel_Verbose)
        TCDebug.setNotificationLog(true)

        TCConsentImplementation.shared.initTCConsent()
        TCServerSideImplementation.initTCServerSide()
    }
    
    var body: some Scene {
        WindowGroup {                                        
            ContentView()
                .environmentObject(modelData)
        }
    }
}
