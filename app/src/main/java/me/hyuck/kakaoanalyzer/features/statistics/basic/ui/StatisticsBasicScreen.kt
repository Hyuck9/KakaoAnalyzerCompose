package me.hyuck.kakaoanalyzer.features.statistics.basic.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.hyuck.kakaoanalyzer.model.Chat

@Composable
fun StatisticsBasicScreen(
    viewModel: StatisticsBasicViewModel,
    chat: Chat,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    viewModel.initChat(chat)

    Box(Modifier.fillMaxSize()) {
        Column {
            Text(text = "분석기간 : ${state.chat.period}")
            Text(text = "총 채팅 횟수  - ${state.messageCount}")
            Text(text = "대화자 수  - ${state.userCount}")
            Text(text = "분석한 키워드 수  - ${state.keywordCount}")
        }
    }
}