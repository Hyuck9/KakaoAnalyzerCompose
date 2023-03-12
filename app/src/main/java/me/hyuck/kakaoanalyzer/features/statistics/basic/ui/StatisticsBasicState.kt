package me.hyuck.kakaoanalyzer.features.statistics.basic.ui

import androidx.compose.runtime.Immutable
import me.hyuck.kakaoanalyzer.model.Chat

@Immutable
data class StatisticsBasicState(
	val chat: Chat = Chat(),
	val userCount: Int = 0,
	val messageCount: Int = 0,
	val keywordCount: Int = 0
)