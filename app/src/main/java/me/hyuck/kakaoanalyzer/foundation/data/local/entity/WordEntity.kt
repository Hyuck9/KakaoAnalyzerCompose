package me.hyuck.kakaoanalyzer.foundation.data.local.entity

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import java.time.LocalDateTime
import java.util.*

@Entity(
	tableName = "words",
	foreignKeys = [ForeignKey(
		entity = ChatEntity::class,
		parentColumns = ["chatId"],
		childColumns = ["chatId"],
		onDelete = CASCADE
	)],
	indices = [Index(value = ["chatId"])]
)
data class WordEntity(
	@PrimaryKey
	@ColumnInfo(name = "wordId")
	val id: String = UUID.randomUUID().toString(),
	@ColumnInfo(name = "chatId")
	val chatId: String,
	@ColumnInfo(name = "userName")
	val userName: String,
	@ColumnInfo(name = "word")
	val word: String,
	@ColumnInfo(name = "dateTime")
	val dateTime: LocalDateTime,
)