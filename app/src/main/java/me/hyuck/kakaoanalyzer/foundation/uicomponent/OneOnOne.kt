package me.hyuck.kakaoanalyzer.foundation.uicomponent

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import me.hyuck.kakaoanalyzer.R
import me.hyuck.kakaoanalyzer.foundation.theme.Lavender10
import me.hyuck.kakaoanalyzer.foundation.theme.MediumRadius
import me.hyuck.kakaoanalyzer.foundation.theme.OneOnOneCaption

@Composable
fun OneOnOneDialog(
    user1Name: String = "상대방",
    user1MessageCount: Int = 0,
    user2Name: String = "회원님",
    user2MessageCount: Int = 0,
    user1FirstMessageCount: String = "0회 (0.00%)",
    user2FirstMessageCount: String = "0회 (0.00%)",
    user1FirstReplyTime: String = "0분 0초",
    user2FirstReplyTime: String = "0분 0초",
    user1AverageReplyTime: String = "0분 0초",
    user2AverageReplyTime: String = "0분 0초",
    allFirstReplyTime: String = "0분 0초",
    allAverageReplyTime: String = "0분 0초",
    setShowDialog: (Boolean) -> Unit = {}
) {
    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(MediumRadius),
            color = Color.White
        ) {
            Column {
                OneOnOneTitle()
                ParticipantRowItem(user1Name, user1MessageCount, user2Name, user2MessageCount)
                ThreeColumnsRowItem(user1FirstMessageCount, stringResource(id = R.string.one_on_one_first_reply_count), user2FirstMessageCount)
                ThreeColumnsRowItem(user1FirstReplyTime, stringResource(id = R.string.one_on_one_first_reply_time), user2FirstReplyTime)
                ThreeColumnsRowItem(user1AverageReplyTime, stringResource(id = R.string.one_on_one_average_reply_time), user2AverageReplyTime)
                ResponseRowItem(allFirstReplyTime, allAverageReplyTime)
                Text(
                    modifier = Modifier.padding(horizontal = 32.dp),
                    text = stringResource(id = R.string.one_on_one_explain),
                    fontSize = 12.sp,
                    color = OneOnOneCaption
                )
                Spacer(modifier = Modifier.requiredHeight(16.dp))
                SimpleTextButton(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    text = stringResource(R.string.button_confirm_label),
                    onClick = { setShowDialog(false) }
                )
                Spacer(modifier = Modifier.requiredHeight(16.dp))
            }
        }
    }
}

@Composable
fun OneOnOneTitle(
    onClickShare: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(0.2f))
        Text(
            modifier = Modifier.weight(0.6f),
            text = stringResource(id = R.string.one_on_one_title),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = Lavender10
        )
        Box(
            modifier = Modifier.weight(0.2f),
            contentAlignment = Alignment.CenterEnd
        ) {
            KakaoAnalyzerIconButton(
                imageVector = Icons.Rounded.Share,
                onClick = onClickShare
            )
        }
    }
}

@Composable
fun ParticipantRowItem(
    leftText: String,
    leftCount: Int,
    rightText: String,
    rightCount: Int
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Left,
                text = leftText
            )
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Right,
                text = rightText
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.comma_number_parent_times, leftCount),
                textAlign = TextAlign.Left,
                fontSize = 12.sp,
                color = OneOnOneCaption
            )
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.comma_number_parent_times, rightCount),
                textAlign = TextAlign.Right,
                fontSize = 12.sp,
                color = OneOnOneCaption
            )
        }
    }
}

@Composable
fun ThreeColumnsRowItem(
    leftText: String,
    centerText: String,
    rightText: String
) {
    Column {
        GradientHorizontalDivider(gradientColors = listOf(Color.White, Color.Black, Color.White))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Left,
                text = leftText
            )
            Text(
                textAlign = TextAlign.Center,
                text = centerText,
                fontSize = 10.sp,
                color = OneOnOneCaption
            )
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Right,
                text = rightText
            )
        }
    }
}

@Composable
fun ResponseRowItem(
    leftText: String,
    rightText: String,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.all_first_reply_time)
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = leftText,
                fontWeight = FontWeight.Bold,
                color = Lavender10
            )
        }

        GradientVerticalDivider(gradientColors = listOf(Color.White, Color.Black, Color.White))

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.all_reply_time)
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = rightText,
                fontWeight = FontWeight.Bold,
                color = Lavender10
            )
        }
    }
}





@Preview
@Composable
private fun OneOnOneTitlePreview() {
    Surface {
        OneOnOneTitle()
    }
}

@Preview
@Composable
private fun ParticipantRowItemPreview() {
    Surface {
        ParticipantRowItem("상대방", 1234, "회원님", 789)
    }
}

@Preview
@Composable
private fun ThreeColumnsRowItemPreview() {
    Surface {
        Column {
            ThreeColumnsRowItem("3회 (73.81%)", "선톡 횟수", "1,321회 (26.19%)")
            ThreeColumnsRowItem("21분 25초", "평균 선톡 답장시간", "9분 30초")
            ThreeColumnsRowItem("7분 10초", "평균 답장시간", "5분 18초")
        }
    }
}

@Preview
@Composable
private fun ResponseRowItemPreview() {
    Surface {
        ResponseRowItem("6분 55초", "12분 31초")
    }
}


@Preview
@Composable
private fun OneOnOnePreview() {
    Surface {
        Column {
            OneOnOneTitle()
            ParticipantRowItem("상대방", 1234, "회원님", 789)
            ThreeColumnsRowItem("3회 (73.81%)", "선톡 횟수", "1,321회 (26.19%)")
            ThreeColumnsRowItem("21분 25초", "평균 선톡 답장시간", "9분 30초")
            ThreeColumnsRowItem("7분 10초", "평균 답장시간", "5분 18초")
            ResponseRowItem("6분 55초", "12분 31초")
            Text(
                modifier = Modifier.padding(horizontal = 32.dp),
                text = stringResource(id = R.string.one_on_one_explain),
                fontSize = 12.sp,
                color = OneOnOneCaption
            )
        }
    }
}

@Preview
@Composable
private fun OneOnOneDialogPreview() {
    androidx.compose.material.Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        OneOnOneDialog()
    }
}