package me.hyuck.kakaoanalyzer.foundation.uicomponent

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.hyuck9.progressitem.ProgressItem
import me.hyuck.kakaoanalyzer.R
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
        percent = chat.progress
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

//@Composable
//fun ProgressChatItemCell(
//    modifier: Modifier = Modifier,
//    percent: Float,
//) {
//    ProgressItem(
//        modifier = modifier
//            .fillMaxWidth()
//            .shadow(elevation = 3.dp, shape = ChatItemShape)
//            .clip(ChatItemShape)
//            .background(ProgressBackground),
//        brush = Brush.horizontalGradient(KakaoAnalyzerTheme.colors.gradient2_2),
//        percent = percent
//    ) {
//        Column(
//            modifier = Modifier
//                .padding(vertical = 16.dp, horizontal = 8.dp)
//        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth(),
//            ) {
//                Text(
//                    modifier = Modifier.weight(1f),
//                    text = "가나다라마바사",
//                    style = MaterialTheme.typography.titleLarge,
//                )
//                Text(text = "123M")
//            }
//            Text(text = "2023-01-30")
//        }
//    }
//}

@Composable
fun StatisticsItemCell(
    @StringRes titleRssId: Int,
    content: String,
    shape: Shape = RectangleShape
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 1.dp),
        shadowElevation = 5.dp,
        shape = shape
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = titleRssId),
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = content,
                color = Color.Gray,                 // TODO: 추후 테마 반영해서 컬러 변경
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun ContentRow(
    modifier: Modifier = Modifier,
    title: String,
    count: Int,
    contentPaddingValues: PaddingValues,
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPaddingValues)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = stringResource(id = R.string.comma_number_times, count),
                color = Color.Gray,                 // TODO: 추후 테마 반영해서 컬러 변경
                style = MaterialTheme.typography.bodySmall
            )
        }
        Divider(
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
        )
    }
}





@Preview
@Composable
private fun ContentRowPreview() {
    Surface {
        ContentRow(
            modifier = Modifier.padding(bottom = 8.dp),
            title = "안녕하십니까",
            count = 1234,
            contentPaddingValues = PaddingValues(all = 8.dp)
        )
    }
}

@Preview
@Composable
private fun StatisticsItemCellPreview() {
    KakaoAnalyzerTheme {
        StatisticsItemCell(
            titleRssId = R.string.sub_title_period,
            content = "2022-04-04 ~ 2023-03-13"
        )
    }
}


//@Preview(showBackground = true)
//@Composable
//private fun NewProgressChatItemCellPreview() {
//    KakaoAnalyzerTheme {
//        Column(
//            modifier = Modifier
//                .padding(16.dp)
//        ) {
//            NewProgressChatItemCell(
//                chat = Chat(
//                    title = "주현욱 님과 카카오톡 대화",
//                    saveDate = "저장한 날짜 : 2023년 2월 7일 오후 1:25",
//                    fileSize = 160_192
//                ),
//                onClick = {}
//            )
//        }
//    }
//}
//@Preview(showBackground = true)
//@Composable
//private fun ProgressChatItemCellPreview() {
//    KakaoAnalyzerTheme {
//        ProgressChatItemCell(
//            percent = 60f
//        )
//    }
//}