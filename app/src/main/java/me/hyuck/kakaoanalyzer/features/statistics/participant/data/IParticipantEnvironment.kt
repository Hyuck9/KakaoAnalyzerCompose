package me.hyuck.kakaoanalyzer.features.statistics.participant.data

import kotlinx.coroutines.flow.Flow
import me.hyuck.kakaoanalyzer.model.Chat
import me.hyuck.kakaoanalyzer.model.Participant

interface IParticipantEnvironment {

	fun getParticipants(chat: Chat): Flow<List<Participant>>

}