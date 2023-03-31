package me.hyuck.kakaoanalyzer.model.mapper

import me.hyuck.kakaoanalyzer.foundation.extension.toStringMinutesSeconds
import me.hyuck.kakaoanalyzer.model.Message
import me.hyuck.kakaoanalyzer.model.ReplyInfo
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
        return "${size}회 (${(size.toFloat() / this@toStringMessageCount.size * 100 * 100).roundToInt() / 100.0}%)"
    }
}