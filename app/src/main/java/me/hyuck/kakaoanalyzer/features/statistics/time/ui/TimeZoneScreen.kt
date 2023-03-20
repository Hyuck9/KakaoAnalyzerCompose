package me.hyuck.kakaoanalyzer.features.statistics.time.ui

import android.graphics.Color
import android.graphics.DashPathEffect
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import me.hyuck.kakaoanalyzer.R
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.TimeZone
import splitties.resources.appStr
import kotlin.streams.toList

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
    AndroidView(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        factory = { context ->
            val lineChart = LineChart(context).apply {
                description.isEnabled = false
                setTouchEnabled(true)
                setDrawGridBackground(false)
                isDragEnabled = true
                setScaleEnabled(true)
                setPinchZoom(true)
                animateX(1500)
                legend.form = Legend.LegendForm.LINE
                data = getLineData(times)
                data.notifyDataChanged()
                invalidate()
                notifyDataSetChanged()
            }
            lineChart
        }
    )
}

private fun getLineData(times: List<TimeZone>): LineData {
    val entries = times.stream()
        .map {
            Entry(it.hour.toFloat(), it.messageCount.toFloat())
        }.toList()

    val dataSet = LineDataSet(entries, appStr(R.string.label_chart_timezone)).apply {
        setDrawIcons(false)
        enableDashedLine(10f, 5f, 0f)

        color = Color.BLACK
        setCircleColor(Color.BLACK)

        lineWidth = 1f
        circleRadius = 3f

        setDrawCircleHole(false)

        formLineWidth = 1f
        formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
        formSize = 15f

        valueTextSize = 9f

        enableDashedHighlightLine(10f, 5f, 0f)

        setDrawFilled(true)
    }

    return LineData(dataSet)
}