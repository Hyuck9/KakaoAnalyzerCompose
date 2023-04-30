package me.hyuck.kakaoanalyzer.features.detail.keyword.ui

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.input.TextFieldValue
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.hyuck.kakaoanalyzer.model.Filter
import me.hyuck.kakaoanalyzer.model.Keyword

@Immutable
data class DetailKeywordState(
    val filters: List<Filter> = listOf(),
    val items: Flow<PagingData<Keyword>> = flow {  },
    val query: TextFieldValue = TextFieldValue("")
)