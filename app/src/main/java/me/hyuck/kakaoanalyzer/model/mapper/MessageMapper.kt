package me.hyuck.kakaoanalyzer.model.mapper

import me.hyuck.kakaoanalyzer.foundation.data.local.entity.MessageEntity
import me.hyuck.kakaoanalyzer.model.Message

fun MessageEntity.toMessage(): Message {
	return Message(
		id = id,
		chatId = chatId,
		dateTime = dateTime,
		userName = userName,
		content = content,
		hour = hour
	)
}

fun Message.toMessageEntity(): MessageEntity {
	return MessageEntity(
		id = id,
		chatId = chatId,
		dateTime = dateTime,
		userName = userName,
		content = content,
		hour = hour
	)
}

fun List<MessageEntity>.toMessages(): List<Message> {
	return map { it.toMessage() }
}

fun List<Message>.toMessageEntities(): List<MessageEntity> {
	return map { it.toMessageEntity() }
}