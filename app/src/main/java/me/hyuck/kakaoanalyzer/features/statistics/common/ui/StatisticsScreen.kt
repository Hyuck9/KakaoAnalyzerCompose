package me.hyuck.kakaoanalyzer.features.statistics.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.hyuck.kakaoanalyzer.R
import me.hyuck.kakaoanalyzer.foundation.theme.KakaoAnalyzerTheme
import me.hyuck.kakaoanalyzer.foundation.uicomponent.StatisticScaffold

@Composable
fun StatisticsScreen(
    chatId: String,
    upPress: () -> Unit
) {
    StatisticScaffold(
        topBar = {
            IconButton(onClick = upPress) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    tint = KakaoAnalyzerTheme.colors.iconInteractive,
                    contentDescription = stringResource(id = R.string.label_back)
                )
            }
        }
    ) {
        Text(
            modifier = Modifier.padding(it),
            text = "Statistics  - $chatId"
        )
    }
}

@Composable
private fun Header() {
    Spacer(
        modifier = Modifier
            .height(280.dp)
            .fillMaxWidth()
            .background(Brush.horizontalGradient(KakaoAnalyzerTheme.colors.tornado1))
    )
}

@Composable
private fun Up(upPress: () -> Unit) {
    IconButton(onClick = upPress) {
        Icon(
            imageVector = Icons.Outlined.ArrowBack,
            tint = KakaoAnalyzerTheme.colors.iconInteractive,
            contentDescription = stringResource(id = R.string.label_back)
        )
    }
}