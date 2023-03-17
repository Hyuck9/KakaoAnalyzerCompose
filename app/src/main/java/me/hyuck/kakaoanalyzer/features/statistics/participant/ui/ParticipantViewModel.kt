package me.hyuck.kakaoanalyzer.features.statistics.participant.ui

import dagger.hilt.android.lifecycle.HiltViewModel
import me.hyuck.kakaoanalyzer.features.base.StatefulViewModel
import javax.inject.Inject

@HiltViewModel
class ParticipantViewModel @Inject constructor(

) : StatefulViewModel<ParticipantState, Unit, Unit, Unit>(ParticipantState(), Unit) {

	override fun dispatch(action: Unit) {
		TODO("Not yet implemented")
	}
}