package me.hyuck.kakaoanalyzer.model

import me.hyuck.kakaoanalyzer.foundation.extension.parseMemory
import java.time.LocalDateTime
import java.util.*
import kotlin.math.roundToInt

data class Chat(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    val saveDate: String = "",
    val fileSize: Long = 0,
    val analysisLine: Int = 0,
    val lineSize: Int = 0,
    val path: String = "",
    val status: ChatStatus = ChatStatus.NEW,
    val startDate: LocalDateTime = LocalDateTime.now(),
    val endDate: LocalDateTime = LocalDateTime.now(),
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
    val progress: Float = 0f
) {
    val fileSizeUnit: String get() = fileSize.parseMemory()
//    val progress: Float get() {
//        return (analysisLine.toFloat() / lineSize.toFloat() * 100f).roundToInt().toFloat()
//    }
}