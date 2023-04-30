package me.hyuck.kakaoanalyzer.features.detail.keyword.data

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.model.Filter
import me.hyuck.kakaoanalyzer.model.Keyword

interface IDetailKeywordEnvironment {

    fun getUsers(chatId: String): Flow<List<String>>

    fun getKeywords(chatId: String, filters: List<Filter>, search: String): Flow<PagingData<Keyword>>
//    fun getKeywords(chatId: String, filters: List<Filter>, search: String): Flow<List<Keyword>>

}