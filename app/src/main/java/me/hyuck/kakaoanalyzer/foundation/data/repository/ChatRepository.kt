package me.hyuck.kakaoanalyzer.foundation.data.repository

import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.foundation.data.local.entity.ChatEntity
import me.hyuck.kakaoanalyzer.model.Chat

interface ChatRepository {

	suspend fun getChatById(chatId: String): Int

	fun getChats(): Flow<List<Chat>>

	suspend fun saveChat(chatEntity: ChatEntity)

}