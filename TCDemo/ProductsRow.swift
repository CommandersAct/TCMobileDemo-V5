//
//  ProductsRow.swift
//  TCDemo-V5
//
//  Created by Abdelhakim SAID on 23/05/2022.
//

import SwiftUI

struct ProductsRow: View
{
    var products:[Product]
    var category: String
    
    var body: some View {
        VStack (alignment: .leading){
            Text(category)
                .font(.largeTitle)
                .padding(.leading, 20)
        
            
            ScrollView(.horizontal, showsIndicators: false)
            {
                HStack
                {
                    ForEach(products, id: \.self)
                    {
                        p in
                        
                        NavigationLink
                        {
                            ProductDetails(product: p)
                        }
                        label:
                        {
                            ProductItem(product: p)
                        }
                        .isDetailLink(false)
                    }
                }
            }
            .padding(.leading, 20)
        }
    }
}

struct ProductsRow_Previews: PreviewProvider {
    static var previews: some View {
        let p1 = Product(name: "Fast Rocket", description: "This is a fast rocket that will enable you to visit other far away galaxies, it has all the latest technologies, brand new.This is a fast rocket that will enable you to visit other far away galaxies, it has all the latest technologies, brand new.This is a fast rocket that will enable you to visit other far away galaxies, it has all the latest technologies, brand new.", isFavorite: true, isInCart: true, price: 150, imageName: "rocket")
        
        let p2 = Product(name: "Puppy", description: "This is a fast rocket that will enable you to visit other far away galaxies, it has all the latest technologies, brand new.This is a fast rocket that will enable you to visit other far away galaxies, it has all the latest technologies, brand new.This is a fast rocket that will enable you to visit other far away galaxies, it has all the latest technologies, brand new.", isFavorite: true, isInCart: true, price: 90, imageName: "dog")
        
        let p3 = Product(name: "Beach Ball", description: "This is a fast rocket that will enable you to visit other far away galaxies, it has all the latest technologies, brand new.This is a fast rocket that will enable you to visit other far away galaxies, it has all the latest technologies, brand new.This is a fast rocket that will enable you to visit other far away galaxies, it has all the latest technologies, brand new.", isFavorite: true, isInCart: true, price: 120, imageName: "ball")
        
        let products = [p1, p2, p3]
        ProductsRow(products: products, category: "Toys")
    }
}
