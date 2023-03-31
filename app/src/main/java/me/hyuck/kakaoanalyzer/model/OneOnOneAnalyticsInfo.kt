package me.hyuck.kakaoanalyzer.model

import me.hyuck.kakaoanalyzer.foundation.extension.durationHoursTo
import me.hyuck.kakaoanalyzer.foundation.extension.durationSecondsTo
import me.hyuck.kakaoanalyzer.foundation.extension.toLocalDateTime
import me.hyuck.kakaoanalyzer.model.mapper.toStringAverageTimes
import me.hyuck.kakaoanalyzer.model.mapper.toStringMessageCount
import java.util.*

data class OneOnOneAnalyticsInfo(
    val allMessages: List<Message> = mutableListOf(),
    val firstMessages: MutableList<Message> = mutableListOf(),
    val firstReplies: MutableList<ReplyInfo> = mutableListOf(),
    val allReplies: MutableList<ReplyInfo> = mutableListOf(),
    val users: List<String> = allMessages.distinctBy { it.userName }.map { it.userName }
) {
    init {
        var preUser = "me.hyuck.kakaoanalyzer.features.statistics.participant.preUser"
        var isFirst = true
        var preDate = Date(0).toLocalDateTime()

        allMessages.forEach { message ->
            if (message.dateTime.durationHoursTo(preDate) >= 1) {
                firstMessages.add(message)
                isFirst = true
            } else if ( preUser != message.userName ) {
                val replyInfo = ReplyInfo(
                    userName = message.userName,
                    replyTime = message.dateTime.durationSecondsTo(preDate)
                )
                if (isFirst) {
                    firstReplies.add(replyInfo)
                    isFirst = false
                }
                allReplies.add(replyInfo)
            }
            preUser = message.userName
            preDate = message.dateTime
        }
    }

    val user1Name: String get() = users[0]
    val user2Name: String get() = users[1]
    val user1MessageCount: Int get() = firstMessages.filter { it.userName == users[0] }.size
    val user2MessageCount: Int get() = firstMessages.filter { it.userName == users[1] }.size
    val user1FirstMessageCount: String get() = firstMessages.toStringMessageCount { it.userName == users[0] }
    val user2FirstMessageCount: String get() = firstMessages.toStringMessageCount { it.userName == users[1] }
    val user1FirstReplyTime: String get() = firstReplies.toStringAverageTimes { it.userName == users[0] }
    val user2FirstReplyTime: String get() = firstReplies.toStringAverageTimes { it.userName == users[1] }
    val user1AverageReplyTime: String get() = allReplies.toStringAverageTimes { it.userName == users[0] }
    val user2AverageReplyTime: String get() = allReplies.toStringAverageTimes { it.userName == users[1] }
    val allFirstReplyTime: String get() = firstReplies.toStringAverageTimes()
    val allAverageReplyTime: String get() = allReplies.toStringAverageTimes()
}