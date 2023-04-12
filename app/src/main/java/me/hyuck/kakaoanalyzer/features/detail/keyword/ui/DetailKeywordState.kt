package me.hyuck.kakaoanalyzer.features.detail.keyword.ui

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.input.TextFieldValue
import me.hyuck.kakaoanalyzer.model.Filter
import me.hyuck.kakaoanalyzer.model.Keyword

@Immutable
data class DetailKeywordState(
    val filters: List<Filter> = listOf(),
    val items: List<Keyword> = listOf(),
    val query: TextFieldValue = TextFieldValue("")
)