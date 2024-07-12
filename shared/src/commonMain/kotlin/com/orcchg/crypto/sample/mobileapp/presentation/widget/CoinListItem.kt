package com.orcchg.crypto.sample.mobileapp.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import com.orcchg.crypto.sample.mobileapp.domain.model.Coin
import com.orcchg.crypto.sample.mobileapp.domain.model.DeltaSign
import com.orcchg.crypto.sample.mobileapp.presentation.theme.Colors
import cryptosamplekmpmobileapp.shared.generated.resources.Res
import cryptosamplekmpmobileapp.shared.generated.resources.ic_fav_off
import cryptosamplekmpmobileapp.shared.generated.resources.ic_fav_on
import org.jetbrains.compose.resources.vectorResource

@Composable
fun CoinListItem(
    coin: Coin,
    positionInList: Int = 0,
    modifier: Modifier = Modifier,
    onClick: (coin: Coin) -> Unit = {},
    onFavouriteClick: (coin: Coin) -> Unit = {}
) {
    val color = if (positionInList % 2 == 0) {
        Colors.surfaceColor
    } else {
        Color.Transparent
    }
    Row(
        modifier = modifier
            .background(
                color = color,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier =
            Modifier
                .wrapContentSize()
                .clickable { onClick(coin) }
                .clip(shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center,
        ) {
            AsyncImage(
                model =
                ImageRequest
                    .Builder(LocalPlatformContext.current)
                    .data(coin.logoUrl)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(52.dp),
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.wrapContentSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.wrapContentSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = coin.symbol.toUpperCase(LocaleList.current.first()),
                    modifier = Modifier.padding(vertical = 3.dp),
                    color = Colors.textColor,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h6,
                )
                Spacer(modifier = Modifier.width(2.dp))
                IconButton(
                    onClick = { onFavouriteClick(coin) },
                    modifier = Modifier.size(16.dp),
                ) {
                    val iconResId = if (coin.isFavourite) {
                        Res.drawable.ic_fav_on
                    } else {
                        Res.drawable.ic_fav_off
                    }
                    Icon(
                        imageVector = vectorResource(iconResId),
                        contentDescription = null,
                    )
                }
            }
            Text(
                text = coin.name,
                modifier = Modifier.padding(vertical = 2.dp),
                color = Colors.textColor,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.caption,
            )
        }
        Spacer(modifier = Modifier.weight(1.0f))
        Column(
            modifier = Modifier.wrapContentSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
        ) {
            val deltaTextColor = when (coin.deltaSign) {
                DeltaSign.MINUS -> Colors.deltaNegativeTextColor
                DeltaSign.PLUS -> Colors.deltaPositiveTextColor
                else -> Colors.disabledTextColor
            }
            Text(
                text = coin.price,
                modifier = Modifier.padding(vertical = 3.dp),
                color = Colors.textColor,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h6,
            )
            Text(
                text = coin.delta,
                modifier = Modifier.padding(vertical = 2.dp),
                color = deltaTextColor,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.caption,
            )
        }
    }
}
