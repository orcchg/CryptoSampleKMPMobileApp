package com.orcchg.crypto.sample.mobileapp.presentation.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.orcchg.crypto.sample.mobileapp.presentation.animation.InfiniteFlipAnimation
import com.orcchg.crypto.sample.mobileapp.presentation.theme.Colors
import cryptosamplekmpmobileapp.shared.generated.resources.Res
import cryptosamplekmpmobileapp.shared.generated.resources.button_retry
import cryptosamplekmpmobileapp.shared.generated.resources.label_error
import org.jetbrains.compose.resources.stringResource

@Composable
fun ErrorState(
    modifier: Modifier = Modifier,
    onRetryPressed: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .drawBehind { drawRect(color = Colors.backgroundColor) }
            .padding(20.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(Res.string.label_error),
                color = Colors.errorColor,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6,
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(
                text = stringResource(Res.string.button_retry),
                onClick = onRetryPressed,
            )
        }
        content()
    }
}

@Composable
fun LoadingState(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .drawBehind { drawRect(color = Colors.backgroundColor) },
    ) {
        ProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun ProgressIndicator(
    modifier: Modifier = Modifier,
    padding: Dp = 20.dp,
    gap: Dp = 8.dp,
    size: Dp = 24.dp,
) {
    Row(
        modifier = modifier
            .wrapContentSize()
            .padding(padding),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        for (i in 0 until 5) {
            if (i != 0) {
                Spacer(modifier = Modifier.width(gap))
            }
            InfiniteFlipAnimation(
                size = size,
                duration = 900,
                backgroundColor = Colors.backgroundColor,
                colorFront = Colors.selectionColor,
                colorBack = Color.DarkGray,
            )
        }
    }
}
