package me.hyuck.kakaoanalyzer.foundation.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun GradientHorizontalDivider(
    gradientColors: List<Color>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(
                brush = Brush.horizontalGradient(gradientColors),
                alpha = 0.3f
            )
    )
}

@Composable
fun GradientVerticalDivider(
    gradientColors: List<Color>
) {
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .width(1.dp)
            .background(
                brush = Brush.verticalGradient(gradientColors),
                alpha = 0.3f
            )
    )
}





@Preview
@Composable
private fun GradientHorizontalDividerPreview() {
    GradientHorizontalDivider(
        gradientColors = listOf(
            Color.White,
            Color.Black,
            Color.White
        )
    )
}

@Preview
@Composable
private fun GradientVerticalDividerPreview() {
    GradientVerticalDivider(
        gradientColors = listOf(
            Color.White,
            Color.Black,
            Color.White
        )
    )
}