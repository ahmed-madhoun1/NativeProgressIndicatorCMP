package com.am.nativeprogressindicatorcmp

import androidx.compose.runtime.Composable

expect fun NativeProgressIndicator()

@Composable
fun NativeProgressIndicatorComponent() {
    NativeProgressIndicator()
}
