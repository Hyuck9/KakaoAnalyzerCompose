package me.hyuck.kakaoanalyzer.features.detail.message.ui

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.input.TextFieldValue
import me.hyuck.kakaoanalyzer.model.Filter
import me.hyuck.kakaoanalyzer.model.Message

@Immutable
data class MessageState(
	val filters: List<Filter> = listOf(),
	val items: List<Message> = listOf(),
	val query: TextFieldValue = TextFieldValue("")
)