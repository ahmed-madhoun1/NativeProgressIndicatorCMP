package com.am.nativeprogressindicatorcmp

import android.widget.ProgressBar
import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.FrameLayout

actual class ProgressIndicator(private val context: Context) {

    private val progressBar: ProgressBar = ProgressBar(context)

    init {
        progressBar.layoutParams = FrameLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
    }

    actual fun show() {
        progressBar.visibility = View.VISIBLE
    }

    actual fun hide() {
        progressBar.visibility = View.GONE
    }

    fun getProgressBar(): ProgressBar = progressBar
}
