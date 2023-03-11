package me.hyuck.kakaoanalyzer.features.statistics.common.data

import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.model.Chat

interface IStatisticsEnvironment {

	fun getChatById(chatId: String): Flow<Chat>

}