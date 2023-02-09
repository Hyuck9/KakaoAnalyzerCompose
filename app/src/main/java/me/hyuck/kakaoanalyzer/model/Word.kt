package me.hyuck.kakaoanalyzer.model

import java.time.LocalDateTime
import java.util.*

data class Word(
	val id: String = UUID.randomUUID().toString(),
	val chatId: String,
	val messageId: String,
	val userName: String,
	val word: String,
	val dateTime: LocalDateTime,
)