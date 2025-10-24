//
//  BannerView.swift
//  TCDemo
//
//  Created by Abdelhakim SAID on 30/05/2022.
//

import SwiftUI
import UIKit
import Foundation

struct BannerView: View {
    @State private var willMoveToNextScreen = false
    @State private var jsonText: String = ""
    @State private var warnings: String = ""
    @State private var consentLanguage: String = ""
    
    @Environment(\.openURL) var openURL
    
    var body: some View {
        #if TC_USE_CONSENT
        GeometryReader { geo in
            VStack(){
                Spacer()
                HStack{
                    Spacer()

                    Button{
                        TCConsentImplementation.shared.acceptAllButtonAction()
                    } label: {
                        HStack{
                            Image("commanders")
                                .resizable().scaledToFit().frame(width: 30, height: 30)
                                .padding(4)
                            Text("Accept All consent")
                                .multilineTextAlignment(.leading)
                                .padding()
                                .padding(.top, -6)
                                .padding(.bottom, -6)
                        }.shadow(color: .gray, radius: 7)
                            .background(Color.black)
                            .cornerRadius(7)
                            .foregroundColor(.white)
                    }
                    
                    Button{
                        TCConsentImplementation.shared.refuseAllButtonAction()
                    } label: {
                        HStack{
                            Image("commanders")
                                .resizable().scaledToFit().frame(width: 30, height: 30)
                                .padding(4)
                            Text("Refuse All consent")
                                .multilineTextAlignment(.leading)
                                .padding()
                                .padding(.top, -6)
                                .padding(.bottom, -6)
                        }.shadow(color: .gray, radius: 7)
                            .background(Color.black)
                            .cornerRadius(7)
                            .foregroundColor(.white)
                    }
                    Spacer()
                }
                
                Button{
                    willMoveToNextScreen.toggle()
                } label: {
                    HStack{
                        Image("commanders")
                            .resizable().scaledToFit().frame(width: 30, height: 30)
                            .padding(4)
                        Text("Show privacy center")
                            .padding()
                    }.shadow(color: .gray, radius: 7)
                        .background(Color.black)
                        .cornerRadius(7)
                        .foregroundColor(.white)
                }
            }
            .sheet(isPresented: $willMoveToNextScreen) {
                TCConsentImplementation.PrivacyCenterWrapper()
            }
            .padding(20)
        }
#endif

    }
}

struct BannerView_Previews: PreviewProvider {
    static var previews: some View {
        BannerView()
    }
}

extension View {
    /// Navigate to a new view.
    /// - Parameters:
    ///   - view: View to navigate to.
    ///   - binding: Only navigates when this condition is `true`.
    func navigate<NewView: View>(to view: NewView, when binding: Binding<Bool>) -> some View {
        NavigationView {
            ZStack {
                self
                    .navigationBarTitle("")
                    .navigationBarHidden(true)
                
                NavigationLink(
                    destination: view
                        .navigationBarTitle("")
                        .navigationBarHidden(true),
                    isActive: binding
                ) {
                    EmptyView()
                }
            }
        }
        .navigationViewStyle(.stack)
    }
}
