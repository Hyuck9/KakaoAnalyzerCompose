package me.hyuck.kakaoanalyzer.foundation.data.repository.message

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import me.hyuck.kakaoanalyzer.foundation.data.local.dao.MessageDao
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.Message
import me.hyuck.kakaoanalyzer.model.Participant
import me.hyuck.kakaoanalyzer.model.TimeZone
import me.hyuck.kakaoanalyzer.model.mapper.toMessageEntities
import me.hyuck.kakaoanalyzer.model.mapper.toMessageEntity
import me.hyuck.kakaoanalyzer.model.mapper.toMessages

class MessageRepositoryImpl(
	private val messageDao: MessageDao,
	private val ioDispatcher: CoroutineDispatcher
) : MessageRepository {

	override fun countUserById(chat: Chat): Flow<Int> {
		return messageDao.countUserById(chat.id, chat.startDate, chat.endDate)
	}

	override fun countMessages(chat: Chat): Flow<Int> {
		return messageDao.countMessages(chat.id, chat.startDate, chat.endDate)
	}

	override fun getParticipants(
		chat: Chat,
		limit: Int
	): Flow<List<Participant>> {
		return messageDao.observeParticipants(chat.id, chat.startDate, chat.endDate, limit)
	}

	override fun getMessages(
		chat: Chat,
		limit: Int
	): Flow<List<Message>> {
		return messageDao
			.observeMessages(chat.id, chat.startDate, chat.endDate, limit)
			.map {
				it.toMessages()
			}
	}

	override fun getMessageCountByTimeZone(chat: Chat): Flow<List<TimeZone>> {
		return messageDao.observeMessageCountByTimeZone(chat.id, chat.startDate, chat.endDate)
	}

	override suspend fun saveMessages(messages: List<Message>) = withContext(ioDispatcher) {
		messageDao.saveMessages(messages.toMessageEntities())
	}

	override suspend fun saveMessage(message: Message) = withContext(ioDispatcher) {
		messageDao.saveMessage(message.toMessageEntity())
	}

}