package me.hyuck.kakaoanalyzer.features.statistics.basic.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.hyuck.kakaoanalyzer.R
import me.hyuck.kakaoanalyzer.foundation.theme.MediumRadius
import me.hyuck.kakaoanalyzer.foundation.uicomponent.BasicTitleCard
import me.hyuck.kakaoanalyzer.foundation.uicomponent.StatisticsItemCell
import me.hyuck.kakaoanalyzer.model.Chat

@Composable
fun StatisticsBasicScreen(
    viewModel: StatisticsBasicViewModel,
    chat: Chat,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    viewModel.initChat(chat)

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        StatisticsBasicInfoCard(
            chatPeriod = state.chat.period,
            messageCount = state.messageCount,
            userCount = state.userCount,
            keywordCount = state.keywordCount,
        )
    }
}

@Composable
fun StatisticsBasicInfoCard(
    modifier: Modifier = Modifier,
    chatPeriod: String = "",
    messageCount: Int = 0,
    userCount: Int = 0,
    keywordCount: Int = 0
) {
    BasicTitleCard(
        modifier = modifier,
        titleResId = R.string.title_analysis_overview,
    ) {
        StatisticsItemCell(
            titleRssId = R.string.sub_title_period,
            content = chatPeriod,
            shape = RoundedCornerShape(topStart = MediumRadius, topEnd = MediumRadius)
        )
        StatisticsItemCell(
            titleRssId = R.string.sub_title_message_count,
            content = stringResource(id = R.string.comma_number_times, messageCount)
        )
        StatisticsItemCell(
            titleRssId = R.string.sub_title_user_count,
            content = stringResource(id = R.string.comma_number_persons, userCount)
        )
        StatisticsItemCell(
            titleRssId = R.string.sub_title_keyword_count,
            content = stringResource(id = R.string.comma_number_cases, keywordCount),
            shape = RoundedCornerShape(bottomStart = MediumRadius, bottomEnd = MediumRadius)
        )
    }
}