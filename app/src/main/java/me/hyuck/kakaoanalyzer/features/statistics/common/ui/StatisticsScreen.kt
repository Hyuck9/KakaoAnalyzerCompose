package me.hyuck.kakaoanalyzer.features.statistics.common.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import me.hyuck.kakaoanalyzer.foundation.uicomponent.StatisticScaffold
import me.hyuck.kakaoanalyzer.foundation.uicomponent.StatisticsBackHeader

@Composable
fun StatisticsScreen(
    chatId: String,
    upPress: () -> Unit
) {
    StatisticScaffold(
        topBar = {
            StatisticsBackHeader(
                onClickBack = upPress,
                onClickShare = {}
            )
        }
    ) {
        Text(
            modifier = Modifier.padding(it),
            text = "Statistics  - $chatId"
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ViewPagerWithTab(
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    Column {

    }
}