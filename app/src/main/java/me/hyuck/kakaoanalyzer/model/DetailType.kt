package me.hyuck.kakaoanalyzer.model

import androidx.annotation.StringRes
import me.hyuck.kakaoanalyzer.R

enum class DetailType(@StringRes val titleResId: Int) {
    KEYWORD(R.string.title_detail_keyword),
    PARTICIPANT(R.string.title_detail_participant)
}