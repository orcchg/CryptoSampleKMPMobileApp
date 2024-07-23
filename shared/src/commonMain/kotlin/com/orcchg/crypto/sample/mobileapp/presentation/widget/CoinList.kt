package com.orcchg.crypto.sample.mobileapp.presentation.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.cash.paging.LoadStateError
import app.cash.paging.LoadStateLoading
import app.cash.paging.LoadStateNotLoading
import app.cash.paging.compose.LazyPagingItems
import com.orcchg.crypto.sample.mobileapp.presentation.model.CoinVo

@Composable
fun CoinList(
    coins: LazyPagingItems<CoinVo>,
    modifier: Modifier = Modifier,
    onItemClick: (coinIndex: Long) -> Unit = {},
    onFavouriteClick: (coinIndex: Long, newIsFavourite: Boolean) -> Unit = { _, _ -> },
    onLoadFinished: () -> Unit = {}
) {
    when (val state = coins.loadState.refresh) {
        LoadStateLoading -> LoadingState()
        is LoadStateError -> ErrorState(
            error = state.error,
            onRetryPressed = coins::retry
        )
        is LoadStateNotLoading ->
            Box(
                modifier = modifier,
                contentAlignment = Alignment.TopCenter
            ) {
                LazyColumn(
                    modifier = Modifier.matchParentSize(),
                    contentPadding = PaddingValues(start = 20.dp, end = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(coins.itemCount) { index ->
                        val coin = coins[index]!!
                        CoinListItem(
                            coin = coin,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            positionInList = index,
                            onClick = onItemClick,
                            onFavouriteClick = onFavouriteClick
                        )
                    }
                }
            }
        else -> error("when should be exhaustive")
    }
    if (coins.loadState.refresh != LoadStateLoading) {
        onLoadFinished()
    }
}
