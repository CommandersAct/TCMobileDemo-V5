//
//  PurchaseValidationView.swift
//  TCDemo
//
//  Created by Abdelhakim SAID on 13/06/2022.
//

import Lottie
import SwiftUI

struct LottieView: UIViewRepresentable {
    var name = "success"
    var loopMode: LottieLoopMode = .playOnce
    @Binding var isActive: Bool
    @EnvironmentObject var tabSelection: Tab
    @EnvironmentObject var modelData: ModelData

    func makeUIView(context: UIViewRepresentableContext<LottieView>) -> UIView {
        let view = UIView(frame: .zero)

        let animationView = LottieAnimationView()
        let animation = LottieAnimation.named(name)
        animationView.animation = animation
        animationView.contentMode = .scaleAspectFit
        animationView.loopMode = loopMode
        animationView.play(completion: {
            finished in
            if finished {
                modelData.resetStrore()
                tabSelection.value = .home
                isActive = false
            }
        })

        animationView.translatesAutoresizingMaskIntoConstraints = false
        view.addSubview(animationView)
        NSLayoutConstraint.activate([
            animationView.heightAnchor.constraint(equalTo: view.heightAnchor),
            animationView.widthAnchor.constraint(equalTo: view.widthAnchor),
        ])

        return view
    }

    func updateUIView(
        _ uiView: UIView,
        context: UIViewRepresentableContext<LottieView>
    ) {
    }
}
