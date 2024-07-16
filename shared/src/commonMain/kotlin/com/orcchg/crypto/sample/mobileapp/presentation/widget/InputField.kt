package com.orcchg.crypto.sample.mobileapp.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.orcchg.crypto.sample.mobileapp.presentation.theme.Colors
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

@Composable
fun InputField(
    hint: StringResource,
    hintIcon: DrawableResource,
    modifier: Modifier = Modifier,
    isFocused: Boolean = false,
    onBackPressed: () -> Unit = {},
    onClearPressed: () -> Unit = {}
) {
    val borderWidth = if (isFocused) 2.dp else 1.dp
    Box(
        modifier = modifier
            .requiredHeight(48.dp)
            .background(
                color = Colors.backgroundColor,
                shape = RoundedCornerShape(40.dp)
            )
            .border(
                width = borderWidth,
                color = Colors.textColor,
                shape = RoundedCornerShape(40.dp)
            )
            .padding(vertical = 12.dp, horizontal = 16.dp),
    ) {}
}
