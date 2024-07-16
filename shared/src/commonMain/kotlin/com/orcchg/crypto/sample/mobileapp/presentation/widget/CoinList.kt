package com.orcchg.crypto.sample.mobileapp.presentation.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.cash.paging.LoadStateError
import app.cash.paging.LoadStateLoading
import app.cash.paging.LoadStateNotLoading
import app.cash.paging.compose.LazyPagingItems
import com.orcchg.crypto.sample.mobileapp.presentation.model.CoinVo

@Composable
fun CoinList(
    coins: LazyPagingItems<CoinVo>,
    onClick: (coinIndex: Long) -> Unit = {},
    onFavouriteClick: (coinIndex: Long) -> Unit = {},
    onLoadFinished: () -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (val loadState = coins.loadState.refresh) {
            LoadStateLoading -> item { CircularProgressIndicator() }
            is LoadStateNotLoading ->
                items(coins.itemCount) { index ->
                    CoinListItem(
                        coin = coins[index]!!,
                        positionInList = index,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        onClick = onClick,
                        onFavouriteClick = onFavouriteClick
                    )
                }
            is LoadStateError -> item {
                Text(
                    text = loadState.error.message.orEmpty(),
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h6
                )
            }
            else -> error("when should be exhaustive")
        }

        if (coins.loadState.refresh != LoadStateLoading) {
            onLoadFinished()
        }
    }
}
