package me.hyuck.kakaoanalyzer.features.home.chats.ui

import androidx.compose.runtime.Immutable
import me.hyuck.kakaoanalyzer.model.Chat

@Immutable
data class ChatsState(
    val items: List<Chat> = listOf()
) {

}