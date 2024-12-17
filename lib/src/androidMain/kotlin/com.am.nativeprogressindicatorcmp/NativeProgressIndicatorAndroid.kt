package com.am.nativeprogressindicatorcmp

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box

@Composable
actual fun NativeProgressIndicator() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(strokeWidth = 4.dp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNativeProgressIndicator() {
    NativeProgressIndicator()
}
