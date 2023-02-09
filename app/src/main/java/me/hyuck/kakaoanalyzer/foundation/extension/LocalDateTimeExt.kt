package me.hyuck.kakaoanalyzer.foundation.extension

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun LocalDateTime.toMillis(): Long {
	val zoneId = ZoneId.systemDefault()
	return atZone(zoneId).toInstant().toEpochMilli()
}

fun Long.toLocalDateTime(): LocalDateTime {
	val zoneId = ZoneId.systemDefault()
	return LocalDateTime.ofInstant(Instant.ofEpochMilli(this), zoneId)
}

fun String.toLocalDateTime(): LocalDateTime {
	return LocalDateTime.parse(this, DateTimeFormatter.ofPattern("yyyy년 M월 d일 a h:mm"))
}
