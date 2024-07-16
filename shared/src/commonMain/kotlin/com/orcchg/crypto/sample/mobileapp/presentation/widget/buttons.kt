package com.orcchg.crypto.sample.mobileapp.presentation.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.orcchg.crypto.sample.mobileapp.presentation.theme.Colors

@Composable
fun OutlinedButton(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = Color.Unspecified,
    outlineColor: Color = defaultOutlineColor(),
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    OutlinedButtonHost(
        modifier = modifier,
        isEnabled = isEnabled,
        color = outlineColor,
        onClick = onClick
    ) {
        Text(
            text = text,
            color = textColor,
            style = MaterialTheme.typography.body1,
        )
    }
}

@Composable
private fun OutlinedButtonHost(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    color: Color = defaultOutlineColor(isEnabled),
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.requiredHeight(40.dp),
        enabled = isEnabled,
        elevation = null,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(
            width = 1.dp,
            color = color,
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Colors.backgroundColor,
            contentColor = Colors.textColor,
            disabledBackgroundColor = Colors.backgroundColor,
            disabledContentColor = Colors.disabledTextColor,
        ),
        content = content
    )
}

@Composable
private fun defaultOutlineColor(isEnabled: Boolean = true): Color =
    if (isEnabled) {
        Colors.textColor
    } else {
        Colors.disabledTextColor
    }
