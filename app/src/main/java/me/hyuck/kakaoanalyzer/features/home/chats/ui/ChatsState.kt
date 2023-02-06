package me.hyuck.kakaoanalyzer.features.home.chats.ui

import androidx.compose.runtime.Immutable
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.mapper.toChatItems

@Immutable
data class ChatsState(
    val items: List<Chat> = listOf()
) {
    val chatItems = items.toChatItems()
}

sealed class ChatItem {
    data class New(
        val chat: Chat
    ) : ChatItem()

    data class InProgress(
        val chat: Chat
    ) : ChatItem()

    data class Complete(
        val chat: Chat
    ) : ChatItem()
}

fun ChatItem.identifier() = when (this) {
    is ChatItem.New -> chat.id
    is ChatItem.InProgress -> chat.id
    is ChatItem.Complete -> chat.id
}