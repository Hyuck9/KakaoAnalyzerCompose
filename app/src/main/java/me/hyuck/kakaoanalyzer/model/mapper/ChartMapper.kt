package me.hyuck.kakaoanalyzer.model.mapper

import android.graphics.Color
import android.graphics.DashPathEffect
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.utils.ColorTemplate.*
import com.github.mikephil.charting.utils.MPPointF
import me.hyuck.kakaoanalyzer.R
import me.hyuck.kakaoanalyzer.model.Keyword
import me.hyuck.kakaoanalyzer.model.Participant
import me.hyuck.kakaoanalyzer.model.TimeZone
import splitties.resources.appDrawable
import splitties.resources.appStr


@JvmName("participantsToPieEntries")
fun List<Participant>.toPieEntries(): List<PieEntry> {
    return map { PieEntry(it.messageCount.toFloat(), it.userName) }
}

@JvmName("keywordsToPieEntries")
fun List<Keyword>.toPieEntries(): List<PieEntry> {
    return this.map { PieEntry(it.wordCount.toFloat(), it.word) }
}

fun List<TimeZone>.toEntries(): List<Entry> {
    return this.map { Entry(it.hour.toFloat(), it.messageCount.toFloat()) }
}

fun List<PieEntry>.toPieData(label: String): PieData {
    val dataSet = PieDataSet(this, label).apply {
        setDrawIcons(false)
        sliceSpace = 3f
        iconsOffset = MPPointF(0f, 40f)
        selectionShift = 5f
        val colorArray = arrayOf(MATERIAL_COLORS, COLORFUL_COLORS, JOYFUL_COLORS)
        colors = colorArray.flatMap { it.toList() }
    }

    return PieData(dataSet)
}

fun List<Entry>.toLineData(): LineData {
    val dataSet = LineDataSet(this, appStr(R.string.label_chart_timezone)).apply {
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