package me.hyuck.kakaoanalyzer.model.mapper

import me.hyuck.kakaoanalyzer.features.home.chats.ui.ChatItem
import me.hyuck.kakaoanalyzer.foundation.data.local.entity.ChatEntity
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.ChatStatus

fun ChatEntity.toChat(): Chat {
    return Chat(
        id = id,
        title = title,
        saveDate = saveDate,
        fileSize = fileSize,
        analysisLine = analysisLine,
        lineSize = lineSize,
        path = path,
        status = status,
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
        saveDate = saveDate,
        fileSize = fileSize,
        analysisLine = analysisLine,
        lineSize = lineSize,
        path = path,
        status = status,
        startDate = startDate,
        endDate = endDate,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun List<ChatEntity>.toChats(): List<Chat> {
    return map { it.toChat() }
}

fun List<Chat>.toChatItems(): List<ChatItem> {
    val chats = this.map {
        when (it.status) {
            ChatStatus.NEW -> ChatItem.New(it)
            ChatStatus.IN_PROGRESS -> ChatItem.InProgress(it)
            ChatStatus.ANALYSIS_COMPLETE -> ChatItem.Complete(it)
        }
    }.toMutableList()

    return chats
}