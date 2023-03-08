package me.hyuck.kakaoanalyzer.features.statistics.common.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
            tabs = state.statisticsTabs
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ViewPagerWithTab(
    modifier: Modifier = Modifier,
    tabs: List<StatisticsTab>
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()



}