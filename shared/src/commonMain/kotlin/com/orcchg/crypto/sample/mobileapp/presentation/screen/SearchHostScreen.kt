package com.orcchg.crypto.sample.mobileapp.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Scaffold
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.orcchg.crypto.sample.mobileapp.di.ServiceLocator
import com.orcchg.crypto.sample.mobileapp.presentation.widget.SearchField

@Composable
fun SearchHostScreen(
    serviceLocator: ServiceLocator,
    onCoinItemClick: (coinIndex: Long) -> Unit = {}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            var searchInput by remember { mutableStateOf("") }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(align = Alignment.CenterVertically)
                    .padding(20.dp)
            ) {
                SearchField(
                    input = searchInput,
                    // TODO: isFocused
                    modifier = Modifier.matchParentSize(),
                    onBackPressed = {
                        // TODO close recent search screen
                    },
                    onClearPressed = { searchInput = "" },
                    onSearchInput = {
                        // TODO: send search query
                    }
                )
            }
        }
    ) {
        CoinTabsScreen(
            serviceLocator = serviceLocator,
            onCoinItemClick = onCoinItemClick
        )
    }
}
