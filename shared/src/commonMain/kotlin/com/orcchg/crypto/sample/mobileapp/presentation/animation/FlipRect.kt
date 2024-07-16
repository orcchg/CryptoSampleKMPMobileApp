package com.orcchg.crypto.sample.mobileapp.presentation.animation

import androidx.compose.runtime.Stable
import androidx.compose.ui.geometry.Rect

@Stable
enum class FlipRectState { FRONT_SHOW, FRONT_HIDE, BACK_HIDE, BACK_SHOW }

@Stable
data class FlipRect(
    val topLeftX: Float,
    val topLeftY: Float,
    val topRightX: Float,
    val topRightY: Float,
    val bottomLeftX: Float,
    val bottomLeftY: Float,
    val bottomRightX: Float,
    val bottomRightY: Float,
) {
    val x = Rect(topLeftX, topRightX, bottomLeftX, bottomRightX)
    val y = Rect(topLeftY, topRightY, bottomLeftY, bottomRightY)
}

@Stable
internal val FrontFlipRectShow =
    FlipRect(
        topLeftX = 0f,
        topLeftY = 0.15f,
        topRightX = 1f,
        topRightY = 0.15f,
        bottomLeftX = 0f,
        bottomLeftY = 1.15f,
        bottomRightX = 1f,
        bottomRightY = 1.15f,
    )

@Stable
internal val FrontFlipRectHide =
    FlipRect(
        topLeftX = 0.5f,
        topLeftY = 0.3f,
        topRightX = 0.5f,
        topRightY = 0f,
        bottomLeftX = 0.5f,
        bottomLeftY = 1f,
        bottomRightX = 0.5f,
        bottomRightY = 1.3f,
    )

@Stable
internal val BackFlipRectShow =
    FlipRect(
        topLeftX = 1f,
        topLeftY = 0.15f,
        topRightX = 0f,
        topRightY = 0.15f,
        bottomLeftX = 1f,
        bottomLeftY = 1.15f,
        bottomRightX = 0f,
        bottomRightY = 1.15f,
    )

@Stable
internal val BackFlipRectHide =
    FlipRect(
        topLeftX = 0.5f,
        topLeftY = 0f,
        topRightX = 0.5f,
        topRightY = 0.3f,
        bottomLeftX = 0.5f,
        bottomLeftY = 1.3f,
        bottomRightX = 0.5f,
        bottomRightY = 1f,
    )
