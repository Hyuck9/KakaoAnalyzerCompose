package me.hyuck.kakaoanalyzer.foundation.data.repository.message

import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.Message
import me.hyuck.kakaoanalyzer.model.Participant

interface MessageRepository {

	fun countUserById(chat: Chat): Flow<Int>

	fun countMessages(chat: Chat): Flow<Int>

	fun getParticipants(chat: Chat, limit: Int = -1): Flow<List<Participant>>

	suspend fun saveMessages(messages: List<Message>)

	suspend fun saveMessage(message: Message)

}