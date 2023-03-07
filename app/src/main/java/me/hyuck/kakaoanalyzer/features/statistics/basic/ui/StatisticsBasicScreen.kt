package me.hyuck.kakaoanalyzer.features.statistics.basic.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun StatisticsBasicScreen(
    chatId: String,
) {
    Box(Modifier.fillMaxSize()) {
        Text(text = "StatisticalBasic  - $chatId")
    }
}