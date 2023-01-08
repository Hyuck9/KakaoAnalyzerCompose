package me.hyuck.kakaoanalyzer.foundation.data.local.converter

import androidx.room.TypeConverter
import me.hyuck.kakaoanalyzer.foundation.extension.toLocalDateTime
import me.hyuck.kakaoanalyzer.foundation.extension.toMillis
import java.time.LocalDateTime

class DateConverter {

	@TypeConverter
	fun toDate(date: Long?): LocalDateTime? {
		if (date == null) return null

		return date.toLocalDateTime()
	}

	@TypeConverter
	fun toDateLong(date: LocalDateTime?): Long? {
		if (date == null) return null

		return date.toMillis()
	}

}