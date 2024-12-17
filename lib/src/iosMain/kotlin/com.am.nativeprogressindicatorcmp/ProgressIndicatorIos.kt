package com.am.nativeprogressindicatorcmp

import platform.UIKit.UIActivityIndicatorView
import platform.UIKit.UILayoutConstraintAxisVertical
import platform.UIKit.UIView
import platform.UIKit.UIViewController
import platform.UIKit.addSubview
import platform.UIKit.startAnimating
import platform.UIKit.stopAnimating

actual class ProgressIndicator(private val viewController: UIViewController) {

    private val activityIndicator = UIActivityIndicatorView()

    init {
        activityIndicator.center = viewController.view.center
        activityIndicator.hidesWhenStopped = true
        viewController.view.addSubview(activityIndicator)
    }

    actual fun show() {
        activityIndicator.startAnimating()
    }

    actual fun hide() {
        activityIndicator.stopAnimating()
    }
}
