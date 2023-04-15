package me.hyuck.kakaoanalyzer.features.detail.keyword.ui

import androidx.compose.ui.text.input.TextFieldValue
import me.hyuck.kakaoanalyzer.model.Filter

sealed class DetailKeywordAction {
	data class ObserveKeywords(val filters: List<Filter>): DetailKeywordAction()
	data class OnQueryChange(val query: TextFieldValue): DetailKeywordAction()
	object OnClearQuery: DetailKeywordAction()
}