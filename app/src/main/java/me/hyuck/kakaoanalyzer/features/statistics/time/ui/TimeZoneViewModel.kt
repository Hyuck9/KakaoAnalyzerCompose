package me.hyuck.kakaoanalyzer.features.statistics.time.ui

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import me.hyuck.kakaoanalyzer.features.base.StatefulViewModel
import me.hyuck.kakaoanalyzer.features.statistics.time.data.ITimeZoneEnvironment
import me.hyuck.kakaoanalyzer.features.statistics.time.data.TimeZoneEnvironment
import me.hyuck.kakaoanalyzer.model.Chat
import javax.inject.Inject

@HiltViewModel
class TimeZoneViewModel @Inject constructor(
    timeZoneEnvironment: TimeZoneEnvironment
) : StatefulViewModel<TimeZoneState, Unit, Unit, ITimeZoneEnvironment>(TimeZoneState(), timeZoneEnvironment) {

    fun initChat(chat: Chat) {
        setState { copy(chat = chat) }
        initTimeZone()
    }

    private fun initTimeZone() {
        viewModelScope.launch {
            environment.getMessageCountByTimeZone(state.value.chat)
                .collect {
                    setState { copy(items = it) }
                }
        }
    }

    override fun dispatch(action: Unit) {
    }

}