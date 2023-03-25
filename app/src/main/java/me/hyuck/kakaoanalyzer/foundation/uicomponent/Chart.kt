package me.hyuck.kakaoanalyzer.foundation.uicomponent

import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.style.StyleSpan
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend

@Composable
fun rememberPieChart(): PieChart {
    val context = LocalContext.current
    val pieChart = remember {
        PieChart(context).apply {
            setUsePercentValues(true)
            description.isEnabled = false
            setExtraOffsets(5f, 10f, 5f, 5f)
            dragDecelerationFrictionCoef = 0.95f
            centerText = SpannableString("분석 결과").apply {
                setSpan(StyleSpan(Typeface.BOLD), 0, length, 0)
            }
            setCenterTextSize(20f)
            isDrawHoleEnabled = true
            setHoleColor(Color.WHITE)
            setTransparentCircleColor(Color.WHITE)
            setTransparentCircleAlpha(110)
            holeRadius = 58f
            transparentCircleRadius = 61f
            setDrawCenterText(true)
            rotationAngle = 270f
            isRotationEnabled = true
            isHighlightPerTapEnabled = true
            animateY(1400, Easing.EaseInOutQuad)

            with(legend) {
                isWordWrapEnabled = true
                xEntrySpace = 7f
                yEntrySpace = 0f
                yOffset = 5f
            }

            setEntryLabelColor(Color.WHITE)
            setEntryLabelTextSize(12f)
        }
    }
    return pieChart
}


@Composable
fun rememberLineChart(): LineChart {
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
    return lineChart
}