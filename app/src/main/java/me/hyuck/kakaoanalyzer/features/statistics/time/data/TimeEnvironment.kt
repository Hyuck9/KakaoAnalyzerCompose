package me.hyuck.kakaoanalyzer.features.statistics.time.data

import me.hyuck.kakaoanalyzer.foundation.data.repository.message.MessageRepository
import javax.inject.Inject

class TimeEnvironment @Inject constructor(
	private val messageRepository: MessageRepository
) : ITimeEnvironment {


}