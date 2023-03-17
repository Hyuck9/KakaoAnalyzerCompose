package me.hyuck.kakaoanalyzer.model

import java.time.LocalDateTime

data class Participant(
	val userName: String,
	val messageCount: Int,
	val firstDate: LocalDateTime,
	val lastDate: LocalDateTime
)
