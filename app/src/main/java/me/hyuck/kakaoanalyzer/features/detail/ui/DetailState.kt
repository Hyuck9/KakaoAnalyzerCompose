package me.hyuck.kakaoanalyzer.features.detail.ui

import androidx.compose.runtime.Immutable
import me.hyuck.kakaoanalyzer.model.Chat

@Immutable
data class DetailState(
	val chat: Chat = Chat()
)