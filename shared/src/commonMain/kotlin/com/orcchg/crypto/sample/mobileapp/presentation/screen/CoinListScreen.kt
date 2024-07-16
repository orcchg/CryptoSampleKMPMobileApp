package com.orcchg.crypto.sample.mobileapp.presentation.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import app.cash.paging.compose.collectAsLazyPagingItems
import com.orcchg.crypto.sample.mobileapp.di.ServiceLocator
import com.orcchg.crypto.sample.mobileapp.presentation.viewmodel.CoinListViewModel
import com.orcchg.crypto.sample.mobileapp.presentation.viewmodel.CoinListViewModelFactory
import com.orcchg.crypto.sample.mobileapp.presentation.widget.CoinList
import dev.materii.pullrefresh.DragRefreshLayout
import dev.materii.pullrefresh.rememberPullRefreshState
import kotlinx.coroutines.launch

@Composable
fun CoinListScreen(
    serviceLocator: ServiceLocator,
    viewModel: CoinListViewModel = CoinListViewModelFactory().create(serviceLocator),
    onClick: (coinIndex: Long) -> Unit = {},
    onFavouriteClick: (coinIndex: Long) -> Unit = {}
) {
    val coins = viewModel.items.collectAsLazyPagingItems()
    val scope = rememberCoroutineScope()
    var isRefreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {
            scope.launch {
                isRefreshing = true
                coins.refresh()
            }
        }
    )
    DragRefreshLayout(
        modifier = Modifier.fillMaxWidth(),
        state = pullRefreshState
    ) {
        CoinList(
            coins = coins,
            onClick = onClick,
            onFavouriteClick = onFavouriteClick,
            onLoadFinished = { isRefreshing = false }
        )
    }
}
