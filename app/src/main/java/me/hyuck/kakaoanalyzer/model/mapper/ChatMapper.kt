package me.hyuck.kakaoanalyzer.model.mapper

import me.hyuck.kakaoanalyzer.foundation.data.local.entity.ChatEntity
import me.hyuck.kakaoanalyzer.model.Chat

fun ChatEntity.toChat(): Chat {
    return Chat(
        id = id,
        title = title,
        size = size,
        path = path,
        startDate = startDate,
        endDate = endDate,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun Chat.toChatEntity(): ChatEntity {
    return ChatEntity(
        id = id,
        title = title,
        size = size,
        path = path,
        startDate = startDate,
        endDate = endDate,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun List<ChatEntity>.toChats(): List<Chat> {
    return map { it.toChat() }
}