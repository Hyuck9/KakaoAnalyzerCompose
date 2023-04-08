package me.hyuck.kakaoanalyzer.model

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf

@Stable
class Filter(
	val name: String,
	enabled: Boolean = false
) {
	val enabled = mutableStateOf(enabled)
}