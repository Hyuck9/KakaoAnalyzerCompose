package me.hyuck.kakaoanalyzer.features.statistics.time.data

import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.TimeZone

interface ITimeZoneEnvironment {

    fun getMessageCountByTimeZone(chat: Chat): Flow<List<TimeZone>>

}