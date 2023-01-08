package me.hyuck.kakaoanalyzer.foundation.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.*

@Entity(tableName = "chats")
data class ChatEntity(
	@PrimaryKey
	@ColumnInfo(name = "chatId")
	val id: String = UUID.randomUUID().toString(),
	@ColumnInfo(name = "chatTitle")
	val title: String = "",
	@ColumnInfo(name = "fileSize")
	val size: Int = 0,
	@ColumnInfo(name = "filePath")
	val path: String = "",
	@ColumnInfo(name = "startDate")
	val startDate: LocalDateTime = LocalDateTime.now(),
	@ColumnInfo(name = "endDate")
	val endDate: LocalDateTime = LocalDateTime.now(),
	@ColumnInfo(name = "createdAt")
	val createdAt: LocalDateTime = LocalDateTime.now(),
	@ColumnInfo(name = "updatedAt")
	val updatedAt: LocalDateTime = LocalDateTime.now(),
)
