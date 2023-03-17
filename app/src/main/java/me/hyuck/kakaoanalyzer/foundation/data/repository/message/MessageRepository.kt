package me.hyuck.kakaoanalyzer.foundation.data.repository.message

import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.Message

interface MessageRepository {

	fun countUserById(chat: Chat): Flow<Int>

	fun countMessages(chat: Chat): Flow<Int>

	suspend fun saveMessages(messages: List<Message>)

	suspend fun saveMessage(message: Message)

}