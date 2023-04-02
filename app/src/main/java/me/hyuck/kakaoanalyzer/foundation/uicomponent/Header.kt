package me.hyuck.kakaoanalyzer.foundation.uicomponent

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.hyuck.kakaoanalyzer.R
import me.hyuck.kakaoanalyzer.foundation.theme.KakaoAnalyzerTheme

@Composable
fun StatisticsBackHeader(
    modifier: Modifier = Modifier,
    @StringRes titleResId: Int = R.string.title_statistics,
    onClickBack: () -> Unit,
    onClickShare: () -> Unit
) {
    KakaoAnalyzerBackHeader(
        modifier = modifier,
        text = stringResource(titleResId),
        onClickBack = onClickBack,
        rightIcon = {
            KakaoAnalyzerIconButton(
                imageVector = Icons.Rounded.Share,
                onClick = onClickShare
            )
        }
    )
}


@Composable
fun KakaoAnalyzerBackHeader(
    modifier: Modifier = Modifier,
    text: String,
    onClickBack: () -> Unit,
    rightIcon: @Composable (()-> Unit)? = null
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier.weight(0.2f)
        ) {
            KakaoAnalyzerIconButton(onClick = onClickBack)
        }

        Text(
            modifier = Modifier.weight(0.6f),
            text = text,
            style = MaterialTheme.typography.titleMedium.copy(textAlign = TextAlign.Center),
            color = Color.Unspecified
        )

        if (rightIcon != null) {
            Box(
                modifier = Modifier
                    .weight(0.2F),
                contentAlignment = Alignment.CenterEnd
            ) {
                rightIcon()
            }
        } else {
            Spacer(
                Modifier
                    .size(0.dp)
                    .weight(0.2f)
            )
        }
    }
}

@Preview
@Composable
private fun StatisticsBackHeaderPreview() {
    KakaoAnalyzerTheme {
        StatisticsBackHeader(
            onClickBack = {},
            onClickShare = {}
        )
    }
}

@Preview
@Composable
private fun KakaoAnalyzerBackHeaderPreview() {
    KakaoAnalyzerTheme {
        KakaoAnalyzerBackHeader(
            text = "테스트",
            onClickBack = {}
        )
    }
}