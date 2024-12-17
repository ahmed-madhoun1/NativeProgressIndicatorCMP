package com.am.nativeprogressindicatorcmp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.material.ProgressIndicator

import platform.Foundation.NSLog
import platform.UIKit.UIActivityIndicatorView
import platform.UIKit.UIActivityIndicatorViewStyleLarge
import platform.UIKit.UIActivityIndicatorViewStyleMedium
import platform.UIKit.UIView
import platform.UIKit.addSubview
import platform.UIKit.frame

@Composable
actual fun NativeProgressIndicator() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        ProgressView()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNativeProgressIndicator() {
    NativeProgressIndicator()
}
