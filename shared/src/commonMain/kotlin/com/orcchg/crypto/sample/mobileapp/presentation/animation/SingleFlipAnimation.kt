package com.orcchg.crypto.sample.mobileapp.presentation.animation

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateRect
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
internal fun SingleFlipAnimationImpl(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    duration: Int = 500,
    backgroundColor: Color,
    colorFront: Color,
    colorBack: Color,
    initialStateProvider: () -> FlipRectState,
) {
    val transition = updateTransition(targetState = initialStateProvider(), label = "state")

    val rectX by transition.animateRect(
        label = "rectX",
        transitionSpec = {
            tween(
                durationMillis = duration,
                easing = LinearEasing,
            )
        },
    ) {
        when (it) {
            FlipRectState.FRONT_SHOW -> FrontFlipRectShow.x
            FlipRectState.FRONT_HIDE -> FrontFlipRectHide.x
            FlipRectState.BACK_SHOW -> BackFlipRectShow.x
            FlipRectState.BACK_HIDE -> BackFlipRectHide.x
        }
    }

    val rectY by transition.animateRect(
        label = "rectY",
        transitionSpec = {
            tween(
                durationMillis = duration,
                easing = LinearEasing,
            )
        },
    ) {
        when (it) {
            FlipRectState.FRONT_SHOW -> FrontFlipRectShow.y
            FlipRectState.FRONT_HIDE -> FrontFlipRectHide.y
            FlipRectState.BACK_SHOW -> BackFlipRectShow.y
            FlipRectState.BACK_HIDE -> BackFlipRectHide.y
        }
    }

    val color by transition.animateColor(
        label = "color",
        transitionSpec = {
            tween(
                durationMillis = duration,
                easing = LinearEasing,
            )
        },
    ) {
        when (it) {
            FlipRectState.FRONT_SHOW -> colorFront
            FlipRectState.FRONT_HIDE -> colorFront
            FlipRectState.BACK_HIDE -> colorBack
            FlipRectState.BACK_SHOW -> colorBack
        }
    }

    val sizeValue = with(LocalDensity.current) { size.toPx() }
    val moreSize = size * 1.25f

    val pointsFront by remember {
        derivedStateOf {
            mutableStateListOf(
                Offset(rectX.left, rectY.left) * sizeValue,
                Offset(rectX.top, rectY.top) * sizeValue,
                Offset(rectX.bottom, rectY.bottom) * sizeValue,
                Offset(rectX.right, rectY.right) * sizeValue,
                Offset(rectX.left, rectY.left) * sizeValue,
            )
        }
    }
    val frontPath by remember {
        derivedStateOf {
            Path().apply {
                pointsFront.forEachIndexed { i, p ->
                    if (i == 0) {
                        moveTo(p.x, p.y)
                    } else {
                        lineTo(p.x, p.y)
                    }
                }
            }
        }
    }

    Canvas(
        modifier
            .progressSemantics()
            .size(width = size, height = moreSize),
    ) {
        drawRect(
            color = backgroundColor,
            size = Size(sizeValue, moreSize.value),
        )
        drawPath(
            path = frontPath,
            color = color,
            style = Fill,
        )
    }
}
