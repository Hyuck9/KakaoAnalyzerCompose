package me.hyuck.kakaoanalyzer.model

import me.hyuck.kakaoanalyzer.foundation.extension.toFormatString
import java.time.LocalDateTime

data class Participant(
	val userName: String,
	val messageCount: Int,
	val firstDate: LocalDateTime,
	val lastDate: LocalDateTime
) {
	val firstDateString: String get() = firstDate.toFormatString("첫번째 메시지 : yyyy-MM-dd HH:mm")
	val lastDateString: String get() = lastDate.toFormatString("마지막 메시지 : yyyy-MM-dd HH:mm")
}
