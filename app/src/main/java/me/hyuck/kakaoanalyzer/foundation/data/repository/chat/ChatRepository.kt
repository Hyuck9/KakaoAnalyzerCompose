package me.hyuck.kakaoanalyzer.foundation.data.repository.chat

import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.foundation.data.local.entity.ChatEntity
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.ChatStatus

interface ChatRepository {

	suspend fun getChatById(chatId: String): Int

	fun getChats(): Flow<List<Chat>>

	suspend fun saveChat(chatEntity: ChatEntity)

	suspend fun updateStatus(chatId: String, chatStatus: ChatStatus)

	suspend fun updateProgress(chatId: String, currentLine: Int)

}