package com.orcchg.crypto.sample.mobileapp.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.orcchg.crypto.sample.mobileapp.presentation.theme.Colors
import cryptosamplekmpmobileapp.shared.generated.resources.Res
import cryptosamplekmpmobileapp.shared.generated.resources.ic_back
import cryptosamplekmpmobileapp.shared.generated.resources.ic_close
import cryptosamplekmpmobileapp.shared.generated.resources.ic_search
import cryptosamplekmpmobileapp.shared.generated.resources.label_search_hint
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

@Composable
fun SearchField(
    input: String = "",
    isFocused: Boolean = false,
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit = {},
    onClearPressed: () -> Unit = {},
    onSearchInput: (input: String) -> Unit = {}
) {
    val (borderWidth, icon) = if (isFocused) {
        2.dp to Res.drawable.ic_back
    } else {
        1.dp to Res.drawable.ic_search
    }
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
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier.matchParentSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackPressed,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = vectorResource(icon),
                    contentDescription = null,
                    tint = Colors.textColor
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            BasicTextField(
                value = input,
                onValueChange = onSearchInput,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1.0f),
                textStyle = MaterialTheme.typography.titleMedium,
//                placeholder = {
//                    Text(
//                        text = stringResource(Res.string.label_search_hint),
//                        color = Colors.textColor,
//                        fontWeight = FontWeight.SemiBold,
//                        style = MaterialTheme.typography.titleMedium
//                    )
//                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                singleLine = true,
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = onClearPressed,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = vectorResource(Res.drawable.ic_close),
                    contentDescription = null,
                    tint = Colors.textColor
                )
            }
        }
    }
}
