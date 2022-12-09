//
//  TCDemoApp.swift
//  TCDemo
//
//  Created by Abdelhakim SAID on 23/05/2022.
//

import SwiftUI

@main
struct TCDemoApp: App {
    
    @StateObject var modelData = ModelData()

    init()
    {
        TCExample.initTCServerSide()
        TCExample.initTCConsent()
    }
    
    var body: some Scene {
        WindowGroup {                                        
            ContentView()
                .environmentObject(modelData)
        }
    }
}
