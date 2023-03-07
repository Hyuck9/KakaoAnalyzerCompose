package me.hyuck.kakaoanalyzer.features.statistics.basic.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.hyuck.kakaoanalyzer.R
import me.hyuck.kakaoanalyzer.foundation.theme.KakaoAnalyzerTheme

@Composable
fun StatisticalBasicScreen(
    chatId: String,
    upPress: () -> Unit
) {
    Box(Modifier.fillMaxSize()) {
        Header()
        Text(text = "StatisticalBasic  - $chatId")
        Up(upPress)
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