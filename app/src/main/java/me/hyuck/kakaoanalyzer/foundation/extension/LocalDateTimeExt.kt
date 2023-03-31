package me.hyuck.kakaoanalyzer.foundation.extension

import me.hyuck.kakaoanalyzer.R
import splitties.resources.appStr
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

fun LocalDateTime.toMillis(): Long {
	return this.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}
fun Long.toLocalDateTime(): LocalDateTime {
	return LocalDateTime.ofInstant(Instant.ofEpochMilli(this), ZoneId.systemDefault())
}

fun LocalDateTime.toDate(): Date {
	return Date.from(this.atZone(ZoneId.systemDefault()).toInstant())
}
fun Date.toLocalDateTime(): LocalDateTime {
	return LocalDateTime.ofInstant(this.toInstant(), ZoneId.systemDefault())
}

fun String.toLocalDateTime(): LocalDateTime {
	return LocalDateTime.parse(this, DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h:mm"))
}
fun LocalDateTime.toFormatString(format: String): String {
	return this.format(DateTimeFormatter.ofPattern(format))
}

fun LocalDateTime.toTimeString(): String {
	val today = LocalDateTime.now()
	val diffDays = ChronoUnit.DAYS.between(this.toLocalDate(), today.toLocalDate()).toInt()
	val diffHour = ChronoUnit.HOURS.between(this, today).toInt()
	val diffMin = ChronoUnit.MINUTES.between(this, today).toInt()
	return when {
		diffDays > 1 -> appStr(R.string.text_days, diffDays)
		diffDays == 1 -> appStr(R.string.text_yesterday)
		diffHour > 0 -> appStr(R.string.text_hours, diffHour)
		diffMin > 0 -> appStr(R.string.text_minutes, diffMin)
		else -> appStr(R.string.text_just_now)
	}
}

fun LocalDateTime.durationHoursTo(previousDateTime: LocalDateTime): Long {
	return ChronoUnit.HOURS.between(previousDateTime, this)
}

fun LocalDateTime.durationSecondsTo(previousDateTime: LocalDateTime): Long {
	return ChronoUnit.SECONDS.between(previousDateTime, this)
}

fun Double.toStringMinutesSeconds(): String {
	return "${(this / 60).toLong()}분 ${(this % 60).toLong()}초"
}