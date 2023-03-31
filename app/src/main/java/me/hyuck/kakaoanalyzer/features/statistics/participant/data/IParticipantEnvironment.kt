package me.hyuck.kakaoanalyzer.features.statistics.participant.data

import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.Message
import me.hyuck.kakaoanalyzer.model.OneOnOneAnalyticsInfo
import me.hyuck.kakaoanalyzer.model.Participant

interface IParticipantEnvironment {

	fun getParticipants(chat: Chat, limit: Int = -1): Flow<List<Participant>>

	fun getMessages(chat: Chat, limit: Int = -1): Flow<List<Message>>

	fun analysisMessages(messages: List<Message>): OneOnOneAnalyticsInfo

}