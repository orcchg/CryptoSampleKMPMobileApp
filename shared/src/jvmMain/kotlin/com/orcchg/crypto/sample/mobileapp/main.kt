package com.orcchg.crypto.sample.mobileapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KMProject",
    ) {
        App()
    }
}