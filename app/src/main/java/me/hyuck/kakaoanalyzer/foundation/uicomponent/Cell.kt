package me.hyuck.kakaoanalyzer.foundation.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import me.hyuck.kakaoanalyzer.foundation.theme.KakaoAnalyzerTheme
import me.hyuck.kakaoanalyzer.foundation.theme.ProgressBackground
import me.hyuck.kakaoanalyzer.foundation.theme.ProgressForeground

@Composable
fun ProgressCell(
    modifier: Modifier = Modifier,
    percent: Float,
    content: @Composable () -> Unit
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .background(ProgressBackground)
    ) {
        Box(
            modifier = Modifier
                .width(width = (maxWidth * percent / 100))
                .background(ProgressForeground)
        ) {
            content()
        }
    }
}



@Preview(showBackground = true)
@Composable
private fun ProgressCellPreview() {
    KakaoAnalyzerTheme {
        ProgressCell(
            percent = 60f,
            content = @Composable {
                Text(
                    text = "테스트입니다",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        )
    }
}