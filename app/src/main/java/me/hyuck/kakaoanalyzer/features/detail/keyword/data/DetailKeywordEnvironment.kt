package me.hyuck.kakaoanalyzer.features.detail.keyword.data

import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.foundation.data.repository.message.MessageRepository
import me.hyuck.kakaoanalyzer.foundation.data.repository.word.WordRepository
import me.hyuck.kakaoanalyzer.model.Filter
import me.hyuck.kakaoanalyzer.model.Keyword
import me.hyuck.kakaoanalyzer.model.toFilterNames
import javax.inject.Inject

class DetailKeywordEnvironment @Inject constructor(
    private val messageRepository: MessageRepository,
    private val wordRepository: WordRepository
) : IDetailKeywordEnvironment {

    override fun getUsers(chatId: String): Flow<List<String>> = messageRepository.getUserNames(chatId)

    override fun getKeywords(
        chatId: String,
        filters: List<Filter>,
        search: String
    ): Flow<List<Keyword>> {
        val userNames = filters.toFilterNames()
        return wordRepository.getKeywords(chatId, userNames, search)
    }

}