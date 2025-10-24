//
//  Toast.swift
//  TCDemo
//
//  Created by Abdelhakim SAID on 14/10/2025.
//

import UIKit

class Toast {
    static func show(message: String, duration: TimeInterval = 4.0) {
        DispatchQueue.main.async {
            guard let window = UIApplication.shared.connectedScenes
                .compactMap({ $0 as? UIWindowScene })
                .flatMap({ $0.windows })
                .first(where: { $0.isKeyWindow }) else { return }
            
            let toastLabel = UILabel()
            toastLabel.backgroundColor = UIColor.black.withAlphaComponent(0.8)
            toastLabel.textColor = .white
            toastLabel.font = .systemFont(ofSize: 14)
            toastLabel.textAlignment = .center
            toastLabel.text = message
            toastLabel.numberOfLines = 0
            toastLabel.alpha = 0
            toastLabel.layer.cornerRadius = 10
            toastLabel.clipsToBounds = true
            
            // Calculate size
            let maxSize = CGSize(width: window.frame.width - 40, height: .greatestFiniteMagnitude)
            let textSize = toastLabel.sizeThatFits(maxSize)
            let labelWidth = min(textSize.width + 40, window.frame.width - 40)
            let labelHeight = textSize.height + 20
            
            toastLabel.frame = CGRect(
                x: (window.frame.width - labelWidth) / 2,
                y: window.frame.height - 150,
                width: labelWidth,
                height: labelHeight
            )
            
            window.addSubview(toastLabel)
            
            // Animate in
            UIView.animate(withDuration: 0.3, animations: {
                toastLabel.alpha = 1.0
            }) { _ in
                // Animate out after duration
                UIView.animate(withDuration: 0.3, delay: duration, options: .curveEaseOut, animations: {
                    toastLabel.alpha = 0.0
                }, completion: { _ in
                    toastLabel.removeFromSuperview()
                })
            }
        }
    }
}
