package me.hyuck.kakaoanalyzer.features.detail.keyword.ui

import me.hyuck.kakaoanalyzer.model.Filter

sealed class DetailKeywordAction {
	data class ObserveKeywords(val filters: List<Filter>): DetailKeywordAction()
}