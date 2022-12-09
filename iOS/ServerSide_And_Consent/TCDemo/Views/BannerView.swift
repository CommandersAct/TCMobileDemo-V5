//
//  BannerView.swift
//  TCDemo
//
//  Created by Abdelhakim SAID on 30/05/2022.
//

import SwiftUI
import TCConsent_IAB

struct BannerView: View {
    @State private var willMoveToNextScreen = false
    @Environment(\.openURL) var openURL
    
    var body: some View {
        VStack(){
            Text("Avec votre consentement, nous utilisons des hits dans cette application pour personnaliser votre expérience. Vous pouvez en savoir plus sur le traitement de vos données dans notre politique de confidentialité et vous pouvez retirer votre consentement ou modifier vos préférences à tout moment.")
                .padding()
                .multilineTextAlignment(.center)
            
            Button("Politique de confidentialité") {
                openURL(URL(string: "https://www.commandersact.com/")!)
            }
            
            Spacer()
            HStack{
                
                Button{
                    TCMobileConsent.sharedInstance().acceptAllConsent()
                    TCMobileConsent.sharedInstance().statViewPrivacyPoliciesFromBanner()
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
                    TCMobileConsent.sharedInstance().refuseAllConsent()
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
            PrivacyCenterWrapper()
        }
        .padding(20)
        
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
