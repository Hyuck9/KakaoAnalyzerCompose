package me.hyuck.kakaoanalyzer.model

import java.time.LocalDateTime
import java.util.*

data class Chat(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    val size: Int = 0,
    val path: String = "",
    val startDate: LocalDateTime = LocalDateTime.now(),
    val endDate: LocalDateTime = LocalDateTime.now(),
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now(),
)