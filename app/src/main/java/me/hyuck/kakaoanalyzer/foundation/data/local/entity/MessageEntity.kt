package me.hyuck.kakaoanalyzer.foundation.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import java.time.LocalDateTime

@Entity(
	tableName = "messages",
	foreignKeys = [ForeignKey(
		entity = ChatEntity::class,
		parentColumns = ["id"],
		childColumns = ["chatId"],
		onDelete = CASCADE
	)],
	indices = [Index(value = ["chatId"])]
)
data class MessageEntity(
	val chatId: String,
	val dateTime: LocalDateTime,
	val userName: String,
	val messageContent: String,
	val hour: Int
)