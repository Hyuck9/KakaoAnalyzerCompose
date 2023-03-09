package me.hyuck.kakaoanalyzer.features.statistics.common.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import me.hyuck.kakaoanalyzer.foundation.uicomponent.StatisticScaffold
import me.hyuck.kakaoanalyzer.foundation.uicomponent.StatisticsBackHeader
import me.hyuck.kakaoanalyzer.model.StatisticsTab

@Composable
fun StatisticsScreen(
    viewModel: StatisticsViewModel,
    chatId: String,
    upPress: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    StatisticScaffold(
        topBar = {
            StatisticsBackHeader(
                onClickBack = upPress,
                onClickShare = {}
            )
        }
    ) {
        ViewPagerWithTab(
            modifier = Modifier.padding(it),
            tabs = state.statisticsTabs,
            selectedTab = state.selectedTab,
            chatId = chatId
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ViewPagerWithTab(
    modifier: Modifier = Modifier,
    tabs: List<StatisticsTab>,
    selectedTab: StatisticsTab,
    chatId: String
) {
    val selectedIndex = tabs.indexOfFirst { it == selectedTab }
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier,
    ) {
        TabRow(
            selectedTabIndex = selectedIndex,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedIndex])
                )
            }
        ) {
            tabs.forEachIndexed { index, statisticsTab ->
                Tab(
                    selected = index == selectedIndex,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = statisticsTab.value
                        )
                    }
                )
            }
        }
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            pageCount = tabs.count(),
            state = pagerState
        ) { page ->
            Text(
                text = "Statistics[${tabs[page].value}]  - $chatId"
            )
        }
    }

}