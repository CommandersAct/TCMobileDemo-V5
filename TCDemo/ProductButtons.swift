//
//  FavoriteButton.swift
//  TCDemo-V5
//
//  Created by Abdelhakim SAID on 20/05/2022.
//

import SwiftUI

struct ProductButtons: View {
    @Binding var isFavorite: Bool
    @Binding var isInCar: Bool

    var body: some View {
        VStack
        {
            Button{
                isFavorite.toggle()
            } label: {
                Label("Save Favorite", systemImage: "heart.fill")
                    .labelStyle(.iconOnly)
                    .scaledToFit()
                    .font(.system(size: 40))
                    .foregroundColor(isFavorite ? .pink : .white)
                    .shadow(color: .black, radius: 7)
            }
            .padding(EdgeInsets(top: 12, leading: 0, bottom: 0, trailing: 0))
            
            Button{
                isInCar.toggle()
            } label: {
                Label("Save Favorite", systemImage: "cart.fill")
                    .labelStyle(.iconOnly)
                    .scaledToFit()
                    .font(.system(size: 40))
                    .foregroundColor(isInCar ? .mint : .gray)
                    .shadow(color: .gray, radius: 7)
            }
            .padding(EdgeInsets(top: 12, leading: 0, bottom: 0, trailing: 0))
        }
    }
}

struct FavoriteButton_Previews: PreviewProvider {
    static var previews: some View {
        ProductButtons(isFavorite: .constant(true), isInCar: .constant(true))
    }
}
