package com.orcchg.crypto.sample.mobileapp.presentation.animation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun InfiniteFlipAnimation(
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    duration: Int = 500,
    startOffset: Int = 0,
    backgroundColor: Color,
    colorFront: Color,
    colorBack: Color,
) {
    val steps = listOf(
        FlipRectState.FRONT_HIDE,
        FlipRectState.BACK_SHOW,
        FlipRectState.BACK_HIDE,
        FlipRectState.FRONT_SHOW,
    )
    var state by remember { mutableStateOf(FlipRectState.FRONT_SHOW) }
    val realDuration = duration / steps.size

    SingleFlipAnimationImpl(
        size = size,
        modifier = modifier,
        duration = realDuration,
        backgroundColor = backgroundColor,
        colorFront = colorFront,
        colorBack = colorBack,
        initialStateProvider = { state },
    )

    LaunchedEffect(key1 = Unit) {
        delay(startOffset.toLong())
        while (true) {
            steps.forEach {
                state = it
                delay(duration.toLong() / 3)
            }
        }
    }
}
