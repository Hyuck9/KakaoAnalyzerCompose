package me.hyuck.kakaoanalyzer.features.statistics.keyword.data

import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.Keyword

interface IKeywordEnvironment {

	fun getKeywords(chat: Chat): Flow<List<Keyword>>

}