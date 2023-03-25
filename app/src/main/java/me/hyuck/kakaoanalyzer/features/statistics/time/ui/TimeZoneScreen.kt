package me.hyuck.kakaoanalyzer.features.statistics.time.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.hyuck.kakaoanalyzer.foundation.uicomponent.rememberLineChart
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.TimeZone
import me.hyuck.kakaoanalyzer.model.mapper.toEntries
import me.hyuck.kakaoanalyzer.model.mapper.toLineData

@Composable
fun TimeZoneScreen(
    viewModel: TimeZoneViewModel = hiltViewModel(),
    chat: Chat
) {
    viewModel.initChat(chat)
    val state by viewModel.state.collectAsStateWithLifecycle()

    TimeZoneContent(times = state.items)
}

@Composable
private fun TimeZoneContent(
    modifier: Modifier = Modifier,
    times: List<TimeZone>
) {
    val lineChart = rememberLineChart()

    LaunchedEffect(times) {
        lineChart.data = times.toEntries().toLineData()
        lineChart.invalidate()
    }

    AndroidView(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        factory = {
            lineChart
        }
    )
}