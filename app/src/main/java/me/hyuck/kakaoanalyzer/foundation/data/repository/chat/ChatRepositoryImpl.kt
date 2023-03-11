package me.hyuck.kakaoanalyzer.foundation.data.repository.chat

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import me.hyuck.kakaoanalyzer.foundation.data.local.dao.ChatDao
import me.hyuck.kakaoanalyzer.foundation.data.local.entity.ChatEntity
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.ChatStatus
import me.hyuck.kakaoanalyzer.model.mapper.toChat
import java.time.LocalDateTime

class ChatRepositoryImpl(
	private val chatDao: ChatDao,
	private val ioDispatcher: CoroutineDispatcher
) : ChatRepository {
	override suspend fun countChatById(chatId: String): Int {
		return chatDao.countChatById(chatId)
	}

	override fun getChats(): Flow<List<Chat>> {
		return chatDao.observeChats()
	}

	override fun getChatById(chatId: String): Flow<Chat> {
		return chatDao.observeChatById(chatId).map {
			it.toChat()
		}
	}

	override suspend fun saveChat(chatEntity: ChatEntity) = withContext(ioDispatcher) {
		chatDao.saveChat(chatEntity)
	}

	override suspend fun updateStatus(chatId: String, chatStatus: ChatStatus) = withContext(ioDispatcher) {
		chatDao.updateStatus(chatId, chatStatus, LocalDateTime.now())
	}

	override suspend fun updateProgress(chatId: String, currentLine: Int) = withContext(ioDispatcher) {
		chatDao.updateProgress(chatId, currentLine, LocalDateTime.now())
	}


}