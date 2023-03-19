package me.hyuck.kakaoanalyzer.model

import me.hyuck.kakaoanalyzer.foundation.extension.isPassedKeyword
import me.hyuck.kakaoanalyzer.foundation.extension.isPassedMessage
import me.hyuck.kakaoanalyzer.foundation.extension.replacePassedMessage
import java.time.LocalDateTime
import java.util.*

data class Message(
	val id: String = UUID.randomUUID().toString(),
	val chatId: String,
	val dateTime: LocalDateTime,
	val userName: String,
	val lineNumber: Int,
	val content: String,
	val hour: Int
) {
	fun parseContentToWords(): List<Word> {
		if (content.isPassedMessage()) {
			return listOf(
				Word(
					chatId = chatId,
					messageId = id,
					userName = userName,
					word = content.replacePassedMessage(),
					dateTime = dateTime
				)
			)
		} else {
			return content.replace("\n", " ").split(" ")
				.filter { it.isPassedKeyword().not() }
				.map {
					Word(
						chatId = chatId,
						messageId = id,
						userName = userName,
						word = it,
						dateTime = dateTime
					)
				}
		}
	}
}