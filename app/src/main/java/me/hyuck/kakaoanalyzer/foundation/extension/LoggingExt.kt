package me.hyuck.kakaoanalyzer.foundation.extension

import timber.log.Timber

fun testLog(message: String?, vararg args: Any?) {
	Timber.tag("TEST").i(message, args)
}