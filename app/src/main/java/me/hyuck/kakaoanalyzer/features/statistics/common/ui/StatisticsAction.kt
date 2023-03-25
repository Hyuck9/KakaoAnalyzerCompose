package me.hyuck.kakaoanalyzer.features.statistics.common.ui

import java.time.LocalDateTime

sealed class StatisticsAction {
	data class ChangeStartDate(val startDate: LocalDateTime) : StatisticsAction()
	data class ChangeEndDate(val endDate: LocalDateTime) : StatisticsAction()
}