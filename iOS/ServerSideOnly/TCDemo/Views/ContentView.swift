//
//  ContentView.swift
//  TCDemo
//
//  Created by Abdelhakim SAID on 23/05/2022.
//

import SwiftUI

struct ContentView: View {
    
    @StateObject var tabSelection: Tab = Tab()
    @State private var isActive : Bool = false
    @EnvironmentObject var modelData: ModelData
    
    var cartProducts: [Product] {
        modelData.products.filter{p in (p.isInCart)}
    }
    
    var body: some View {
        NavigationView {
            TabView(selection: $tabSelection.value) {
                
                HomeView().tabItem {
                    Image(systemName: "house.fill")
                    Text("Acceuil")
                }
                .tag(TabEnum.home)
                .onAppear{
                    TCExample.sendPageViewEvent(pageName: "home_page", pageType: "preview_page")
                }
                
                Cart(isActive: self.$isActive)
                    .tabItem{
                        Image(systemName: "cart.fill")
                        Text("Panier")
                    }
                    .tag(TabEnum.cart)
                    .onAppear{
                        TCExample.sendPageViewEvent(pageName: "cart_page", pageType: "preview_page")
                    }
            }
        }
        .environmentObject(tabSelection)
        
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        
        ContentView()
            .environmentObject(ModelData())
    }
}
