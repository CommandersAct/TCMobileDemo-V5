//
//  Product.swift
//  TCDemo-V5
//
//  Created by Abdelhakim SAID on 20/05/2022.
//

import Foundation
import SwiftUI

struct Product: Hashable, Codable
{
    internal init(name: String, description: String, isFavorite: Bool, isInCart: Bool, price: Int, imageName: String) {
        self.name = name
        self.description = description
        self.isFavorite = isFavorite
        self.isInCart = isInCart
        self.price = price
        self.imageName = imageName
    }
    
    enum ProductCategory : String, Codable {
        case TOYS = "toys"
        case VIDEO_GAMES = "video_games"
        case GOODIES = "goodies"
    }
    
    var name: String
    var description: String
    var isFavorite: Bool
    var isInCart: Bool
    var price: Int
    var category: ProductCategory = ProductCategory.TOYS
    private var imageName: String
    
    var image: Image {
        Image(imageName)
    }
}
