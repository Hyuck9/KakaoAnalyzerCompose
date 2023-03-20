package me.hyuck.kakaoanalyzer.features.statistics.time.ui

import dagger.hilt.android.lifecycle.HiltViewModel
import me.hyuck.kakaoanalyzer.features.base.StatefulViewModel
import me.hyuck.kakaoanalyzer.features.statistics.time.data.ITimeEnvironment
import me.hyuck.kakaoanalyzer.features.statistics.time.data.TimeEnvironment
import javax.inject.Inject

@HiltViewModel
class TimeViewModel @Inject constructor(
    timeEnvironment: TimeEnvironment
) : StatefulViewModel<TimeState, Unit, Unit, ITimeEnvironment>(TimeState(), timeEnvironment) {

    override fun dispatch(action: Unit) {
        TODO("Not yet implemented")
    }

}