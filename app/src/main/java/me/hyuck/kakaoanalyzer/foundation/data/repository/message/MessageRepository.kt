package me.hyuck.kakaoanalyzer.foundation.data.repository.message

import me.hyuck.kakaoanalyzer.model.Message

interface MessageRepository {

	// TODO: 테스트 후 function 정리
	suspend fun getMaxLine(chatId: String): Int
	suspend fun saveMessages(messages: List<Message>)

	suspend fun saveMessage(message: Message)

}