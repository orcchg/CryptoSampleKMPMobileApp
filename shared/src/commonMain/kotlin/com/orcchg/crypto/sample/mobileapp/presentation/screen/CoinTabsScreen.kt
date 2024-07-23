package com.orcchg.crypto.sample.mobileapp.presentation.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.orcchg.crypto.sample.mobileapp.di.ServiceLocator
import com.orcchg.crypto.sample.mobileapp.domain.CoinListResults
import com.orcchg.crypto.sample.mobileapp.presentation.theme.Colors
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CoinTabsScreen(
    serviceLocator: ServiceLocator,
    modifier: Modifier = Modifier,
    onCoinItemClick: (coinIndex: Long) -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val scope = rememberCoroutineScope()
        val pagerState = rememberPagerState(pageCount = { Tabs.entries.size })
        val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }

        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex.value,
            modifier = Modifier
                .wrapContentWidth()
                .padding(bottom = 12.dp)
                .requiredHeight(32.dp),
            edgePadding = 4.dp
        ) {
            Tabs.entries.forEachIndexed { index, tab ->
                Tab(
                    selected = selectedTabIndex.value == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(tab.ordinal)
                        }
                    },
                    selectedContentColor = Colors.textColor,
                    unselectedContentColor = Colors.disabledTextColor,
                    text = {
                        val tabText = stringResource(tab.resId)
                        if (selectedTabIndex.value == index) {
                            Text(
                                text = tabText,
                                color = Colors.textColor,
                                fontSize = TextUnit(28f, TextUnitType.Sp),
                                fontWeight = FontWeight.Bold,
                                lineHeight = TextUnit(32f, TextUnitType.Sp),
                                style = MaterialTheme.typography.titleLarge
                            )
                        } else {
                            Text(
                                text = tabText,
                                modifier = Modifier.padding(top = 4.dp),
                                color = Colors.disabledTextColor,
                                fontWeight = FontWeight.Bold,
                                lineHeight = TextUnit(32f, TextUnitType.Sp),
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            beyondBoundsPageCount = Tabs.entries.size
        ) {
            CoinListScreen(
                serviceLocator = serviceLocator,
                resultsType =
                    when (Tabs.entries[it]) {
                        Tabs.ALL -> CoinListResults.ALL
                        Tabs.FAVOURITES -> CoinListResults.FAVOURITE
                    },
                onItemClick = onCoinItemClick
            )
        }
    }
}
