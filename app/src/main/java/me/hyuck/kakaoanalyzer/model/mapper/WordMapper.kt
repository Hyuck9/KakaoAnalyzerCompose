package me.hyuck.kakaoanalyzer.model.mapper

import me.hyuck.kakaoanalyzer.foundation.data.local.entity.WordEntity
import me.hyuck.kakaoanalyzer.model.Word

fun WordEntity.toWord(): Word {
	return Word(
		id = id,
		chatId = chatId,
		messageId = messageId,
		userName = userName,
		word = word,
		dateTime = dateTime
	)
}

fun Word.toWordEntity(): WordEntity {
	return WordEntity(
		id = id,
		chatId = chatId,
		messageId = messageId,
		userName = userName,
		word = word,
		dateTime = dateTime
	)
}

fun List<WordEntity>.toWords(): List<Word> {
	return map { it.toWord() }
}

fun List<Word>.toWordEntities(): List<WordEntity> {
	return map { it.toWordEntity() }
}