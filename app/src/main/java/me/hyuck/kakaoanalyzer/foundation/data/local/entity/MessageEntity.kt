package me.hyuck.kakaoanalyzer.foundation.data.local.entity

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import java.time.LocalDateTime
import java.util.*

@Entity(
	tableName = "messages",
	foreignKeys = [ForeignKey(
		entity = ChatEntity::class,
		parentColumns = ["chatId"],
		childColumns = ["chatId"],
		onDelete = CASCADE
	)],
	indices = [Index(value = ["chatId"])]
)
data class MessageEntity(
	@PrimaryKey
	@ColumnInfo(name = "messageId")
	val id: String = UUID.randomUUID().toString(),
	@ColumnInfo(name = "chatId")
	val chatId: String,
	@ColumnInfo(name = "dateTime")
	val dateTime: LocalDateTime,
	@ColumnInfo(name = "userName")
	val userName: String,
	@ColumnInfo(name = "lineNumber")
	val lineNumber: Int,
	@ColumnInfo(name = "content")
	val content: String,
	@ColumnInfo(name = "hour")
	val hour: Int
)