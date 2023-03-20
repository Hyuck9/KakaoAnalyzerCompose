package me.hyuck.kakaoanalyzer.features.statistics.time.ui

import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.TimeZone

data class TimeZoneState(
    val chat: Chat = Chat(),
    val items: List<TimeZone> = listOf()
)