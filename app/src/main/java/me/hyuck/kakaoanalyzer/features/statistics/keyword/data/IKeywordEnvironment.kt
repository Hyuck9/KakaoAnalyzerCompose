package me.hyuck.kakaoanalyzer.features.statistics.keyword.data

import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.Keyword

interface IKeywordEnvironment {

	fun getUsers(chat: Chat): Flow<List<String>>

	fun getKeywords(chat: Chat, limit: Int = -1): Flow<List<Keyword>>

}