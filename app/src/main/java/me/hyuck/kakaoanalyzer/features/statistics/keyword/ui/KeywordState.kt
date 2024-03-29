package me.hyuck.kakaoanalyzer.features.statistics.keyword.ui

import androidx.compose.runtime.Immutable
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.Filter
import me.hyuck.kakaoanalyzer.model.Keyword

@Immutable
data class KeywordState(
	val chat: Chat = Chat(),
	val filters: List<Filter> = listOf(),
	val items: List<Keyword> = listOf()
)