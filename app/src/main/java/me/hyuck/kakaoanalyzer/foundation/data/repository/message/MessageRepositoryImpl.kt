package me.hyuck.kakaoanalyzer.foundation.data.repository.message

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import me.hyuck.kakaoanalyzer.foundation.data.local.dao.MessageDao
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.Message
import me.hyuck.kakaoanalyzer.model.mapper.toMessageEntities
import me.hyuck.kakaoanalyzer.model.mapper.toMessageEntity

class MessageRepositoryImpl(
	private val messageDao: MessageDao,
	private val ioDispatcher: CoroutineDispatcher
) : MessageRepository {

	// TODO: 테스트 후 function 정리
	override suspend fun getMaxLine(chatId: String): Int {
		return messageDao.getMaxLine(chatId) ?: 0
	}

	override fun countUserById(chat: Chat): Flow<Int> {
		return messageDao.countUserById(chat.id, chat.startDate, chat.endDate)
	}

	override fun countMessages(chat: Chat): Flow<Int> {
		return messageDao.countMessages(chat.id, chat.startDate, chat.endDate)
	}

	override suspend fun saveMessages(messages: List<Message>) = withContext(ioDispatcher) {
		messageDao.saveMessages(messages.toMessageEntities())
	}

	override suspend fun saveMessage(message: Message) = withContext(ioDispatcher) {
		messageDao.saveMessage(message.toMessageEntity())
	}

}