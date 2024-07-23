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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.orcchg.crypto.sample.mobileapp.presentation.model.CoinVo
import com.orcchg.crypto.sample.mobileapp.presentation.model.DeltaSign
import com.orcchg.crypto.sample.mobileapp.presentation.theme.Colors
import cryptosamplekmpmobileapp.shared.generated.resources.Res
import cryptosamplekmpmobileapp.shared.generated.resources.ic_fav_off
import cryptosamplekmpmobileapp.shared.generated.resources.ic_fav_on
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun CoinListItem(
    coin: CoinVo,
    modifier: Modifier = Modifier,
    positionInList: Int = 0,
    onClick: (coinIndex: Long) -> Unit = {},
    onFavouriteClick: (coinIndex: Long, newIsFavourite: Boolean) -> Unit = { _, _ -> }
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
                .clickable { onClick(coin.index) }
                .clip(shape = RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model =
                ImageRequest
                    .Builder(LocalPlatformContext.current)
                    .data(coin.logoUrl)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(52.dp)
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
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.width(2.dp))

                var isFavourite by remember { mutableStateOf(coin.isFavourite) }

                IconButton(
                    onClick = {
                        isFavourite = !coin.isFavourite
                        onFavouriteClick(coin.index, isFavourite)
                    },
                    modifier = Modifier.size(16.dp)
                ) {
                    val (iconResId, iconTint) = if (isFavourite || coin.isFavourite) {
                        Res.drawable.ic_fav_on to Colors.favColor
                    } else {
                        Res.drawable.ic_fav_off to Colors.disabledTextColor
                    }
                    Icon(
                        imageVector = vectorResource(iconResId),
                        contentDescription = null,
                        tint = iconTint,
                    )
                }
            }
            Text(
                text = coin.name,
                modifier = Modifier.padding(vertical = 2.dp),
                color = Colors.textColor,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.bodySmall
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
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = coin.delta,
                modifier = Modifier.padding(vertical = 2.dp),
                color = deltaTextColor,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview
@Composable
fun CoinListItemPreview() {
    CoinListItem(
        coin = CoinVo(
            index = 0L,
            name = "Bitcoin",
            symbol = "BTC",
            url = "https://bitcoin.org",
            logoUrl = "https://raw.githubusercontent.com/trustwallet/assets/master/blockchains/bitcoin/info/logo.png",
            price = "$60.345,28",
            delta = "$1.210,41",
            deltaSign = DeltaSign.PLUS,
            isFavourite = true
        )
    )
}
