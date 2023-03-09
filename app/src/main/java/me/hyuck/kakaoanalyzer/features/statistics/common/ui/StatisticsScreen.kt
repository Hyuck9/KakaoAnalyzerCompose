package me.hyuck.kakaoanalyzer.features.statistics.common.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
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
            chatId = chatId
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ViewPagerWithTab(
    modifier: Modifier = Modifier,
    tabs: List<StatisticsTab>,
    chatId: String
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage])
                )
            }
        ) {
            tabs.forEachIndexed { index, statisticsTab ->
                Tab(
                    selected = index == pagerState.currentPage,
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
            pageCount = tabs.count(),
            state = pagerState
        ) { page ->
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Statistics[${tabs[page].value}]  - $chatId"
                )
            }
        }
    }

}