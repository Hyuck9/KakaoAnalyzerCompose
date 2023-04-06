package me.hyuck.kakaoanalyzer.features.detail.ui

import androidx.compose.runtime.Immutable
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.DetailType

@Immutable
data class DetailState(
	val chatId: String = "",
	val detailType: DetailType = DetailType.KEYWORD,
)