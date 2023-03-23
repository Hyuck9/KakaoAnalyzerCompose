package me.hyuck.kakaoanalyzer.model.mapper

import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate.*
import com.github.mikephil.charting.utils.MPPointF
import me.hyuck.kakaoanalyzer.model.Keyword
import me.hyuck.kakaoanalyzer.model.Participant


@JvmName("participantsToPieEntries")
fun List<Participant>.toPieEntries(): List<PieEntry> {
    return map { PieEntry(it.messageCount.toFloat(), it.userName) }
}

@JvmName("keywordsToPieEntries")
fun List<Keyword>.toPieEntries(): List<PieEntry> {
    return this.map { PieEntry(it.wordCount.toFloat(), it.word) }
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