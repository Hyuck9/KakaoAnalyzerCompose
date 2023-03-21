package me.hyuck.kakaoanalyzer.features.statistics.time.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.TimeZone
import me.hyuck.kakaoanalyzer.model.getLineData

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
    val context = LocalContext.current
    val lineChart = remember {
        LineChart(context).apply {
            description.isEnabled = false
            setTouchEnabled(true)
            setDrawGridBackground(false)
            isDragEnabled = true
            setScaleEnabled(true)
            setPinchZoom(true)
            animateX(1500)
            legend.form = Legend.LegendForm.LINE
        }
    }

    LaunchedEffect(times) {
        lineChart.data = times.getLineData()
        lineChart.data.notifyDataChanged()
        lineChart.invalidate()
        lineChart.notifyDataSetChanged()
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