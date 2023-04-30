package me.hyuck.kakaoanalyzer.features.detail.keyword.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
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
    ): Flow<PagingData<Keyword>> {
        val userNames = filters.toFilterNames()

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 10,
                initialLoadSize = 20
            ),
            pagingSourceFactory = {
                wordRepository.getKeywords(chatId, userNames, search)
            }
        ).flow

//        return wordRepository.getKeywords(chatId, userNames, search)
    }

}