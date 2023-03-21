package me.hyuck.kakaoanalyzer.model

import android.graphics.Color
import android.graphics.DashPathEffect
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import me.hyuck.kakaoanalyzer.R
import splitties.resources.appDrawable
import splitties.resources.appStr

data class TimeZone(
    val hour: String,
    val messageCount: Int
)

fun List<TimeZone>.getLineData(): LineData {
    val entries = this.map { Entry(it.hour.toFloat(), it.messageCount.toFloat()) }

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
        fillDrawable = appDrawable(R.drawable.shape_vertical_gradient)
    }

    return LineData(dataSet)
}