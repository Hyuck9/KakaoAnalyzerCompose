package me.hyuck.kakaoanalyzer.foundation.data.repository

import me.hyuck.kakaoanalyzer.foundation.data.local.entity.ChatEntity

interface ChatRepository {

	suspend fun getChatById(chatId: String): Int
	suspend fun saveChat(chatEntity: ChatEntity)

}