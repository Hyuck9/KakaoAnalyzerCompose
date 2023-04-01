package me.hyuck.kakaoanalyzer.model.mapper

import me.hyuck.kakaoanalyzer.R
import me.hyuck.kakaoanalyzer.foundation.extension.toStringMinutesSeconds
import me.hyuck.kakaoanalyzer.model.Message
import me.hyuck.kakaoanalyzer.model.ReplyInfo
import splitties.resources.appStr
import kotlin.math.roundToInt

fun List<ReplyInfo>.toStringAverageTimes(): String {
    return map { it.replyTime }
        .average()
        .toStringMinutesSeconds()
}

fun List<ReplyInfo>.toStringAverageTimes(predicate: (ReplyInfo) -> Boolean): String {
    return filter { predicate(it) }.toStringAverageTimes()
}

fun List<Message>.toStringMessageCount(predicate: (Message) -> Boolean): String {
    with(this.filter { predicate(it) }) {
        val rate = (size.toFloat() / this@toStringMessageCount.size * 100 * 100).roundToInt() / 100.0
        return appStr(R.string.comma_number_first_reply_count, size, rate)
    }
}