//
//  ProductDetails.swift
//  TCDemo-V5
//
//  Created by Abdelhakim SAID on 20/05/2022.
//

import SwiftUI

struct ProductDetails: View {
    @State var product: Product
    
    let backgroundGradient = LinearGradient(
        colors: [Color.red],
        startPoint: .top, endPoint: .bottom)

    var body: some View {
        ScrollView
        {
            CircleImageView(image: product.image)
                .frame(minWidth: 0,
                       maxWidth: .infinity,
                       minHeight: 0,
                       maxHeight: .infinity,
                       alignment: .center)
                .padding(.top, 20)
                .padding(.bottom, 24)
                .background(.mint)

            VStack(alignment: .leading)
            {
                VStack {
                    Text(product.name)
                        .font(.title)
                    
                    Text("Price : \(product.price) €")
                        .font(.title2)
                        .foregroundColor(.green)
                        .bold()
                }
                
                Divider()

                HStack(alignment: .top){

                    Text(product.description)
                        .padding()
                    
                    Spacer()
                    
                    ProductButtons(isFavorite: $product.isFavorite, isInCar: $product.isInCart)
                }
            }.padding()

        }
    }
}

struct ProductDetails_Previews: PreviewProvider {
    static var previews: some View {
        ProductDetails(product: Product(name: "Fast Rocket", description: "This is a fast rocket that will enable you to visit other far away galaxies, it has all the latest technologies, brand new.This is a fast rocket that will enable you to visit other far away galaxies, it has all the latest technologies, brand new.This is a fast rocket that will enable you to visit other far away galaxies, it has all the latest technologies, brand new.", isFavorite: true, isInCart: true, price: 150, imageName: "rocket"))
    }
}
