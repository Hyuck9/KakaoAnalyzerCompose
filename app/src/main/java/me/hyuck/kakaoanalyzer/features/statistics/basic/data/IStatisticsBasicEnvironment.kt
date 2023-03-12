package me.hyuck.kakaoanalyzer.features.statistics.basic.data

import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.model.Chat

interface IStatisticsBasicEnvironment {

	fun getUserCount(chat: Chat): Flow<Int>
	fun getMessageCount(chat: Chat): Flow<Int>
	fun getKeywordCount(chat: Chat): Flow<Int>

}