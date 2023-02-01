package me.hyuck.kakaoanalyzer.foundation.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.hyuck.kakaoanalyzer.foundation.theme.KakaoAnalyzerTheme
import me.hyuck.kakaoanalyzer.foundation.theme.ProgressBackground

private val ChatItemShape = RoundedCornerShape(10.dp)
@Composable
fun ProgressChatItemCell(
    modifier: Modifier = Modifier,
    percent: Float,
) {
    ProgressCell(
        percent = percent,
        content = @Composable {
            Column(
                modifier = modifier
                    .padding(vertical = 16.dp, horizontal = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "가나다라마바사",
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Text(text = "123M")
                }
                Text(text = "2023-01-30")
            }
        }
    )
}

@Composable
fun ProgressCell(
    modifier: Modifier = Modifier,
    percent: Float,
    content: @Composable () -> Unit
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .shadow(elevation = 3.dp, shape = ChatItemShape)
            .clip(ChatItemShape)
            .background(ProgressBackground),
        contentAlignment = Alignment.CenterStart
    ) {
        val percentWidth = (maxWidth * percent / 100)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Box(
                modifier = Modifier
                    .width(width = percentWidth)
                    .fillMaxHeight()
                    .background(Brush.horizontalGradient(KakaoAnalyzerTheme.colors.gradient2_2))
            )
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
@Preview(showBackground = true)
@Composable
private fun ProgressChatItemCellPreview() {
    KakaoAnalyzerTheme {
        ProgressChatItemCell(
            percent = 60f
        )
    }
}