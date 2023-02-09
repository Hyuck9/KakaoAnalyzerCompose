package me.hyuck.kakaoanalyzer.foundation.data.repository.message

import me.hyuck.kakaoanalyzer.model.Message

interface MessageRepository {

	suspend fun saveMessages(messages: List<Message>)

	suspend fun saveMessage(message: Message)

}