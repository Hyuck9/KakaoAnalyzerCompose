package me.hyuck.kakaoanalyzer.foundation.uicomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.hyuck9.progressitem.ProgressItem
import me.hyuck.kakaoanalyzer.foundation.theme.KakaoAnalyzerTheme
import me.hyuck.kakaoanalyzer.foundation.theme.ProgressBackground
import me.hyuck.kakaoanalyzer.model.Chat

private val ChatItemShape = RoundedCornerShape(10.dp)

@Composable
fun NewProgressChatItemCell(
    modifier: Modifier = Modifier,
    chat: Chat,
    onClick: () -> Unit
) {
    ProgressItem(
        modifier = modifier
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .shadow(elevation = 3.dp, shape = ChatItemShape)
            .clip(ChatItemShape)
            .background(ProgressBackground),
        brush = Brush.horizontalGradient(KakaoAnalyzerTheme.colors.gradient2_2),
    ) {
        Column(
            modifier = Modifier
                .padding(all = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = chat.title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = chat.fileSizeUnit,
                    color = KakaoAnalyzerTheme.colors.textHelp,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Text(
                text = chat.saveDate,
                color = KakaoAnalyzerTheme.colors.brand,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun ProgressChatItemCell(
    modifier: Modifier = Modifier,
    percent: Float,
) {
    ProgressItem(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 3.dp, shape = ChatItemShape)
            .clip(ChatItemShape)
            .background(ProgressBackground),
        brush = Brush.horizontalGradient(KakaoAnalyzerTheme.colors.gradient2_2),
        percent = percent
    ) {
        Column(
            modifier = Modifier
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
}




@Preview(showBackground = true)
@Composable
private fun NewProgressChatItemCellPreview() {
    KakaoAnalyzerTheme {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            NewProgressChatItemCell(
                chat = Chat(
                    title = "주현욱 님과 카카오톡 대화",
                    saveDate = "저장한 날짜 : 2023년 2월 7일 오후 1:25",
                    fileSize = 160_192
                ),
                onClick = {}
            )
        }
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