package me.hyuck.kakaoanalyzer.features.detail.ui

import dagger.hilt.android.lifecycle.HiltViewModel
import me.hyuck.kakaoanalyzer.features.base.StatefulViewModel
import me.hyuck.kakaoanalyzer.features.detail.data.DetailEnvironment
import me.hyuck.kakaoanalyzer.features.detail.data.IDetailEnvironment
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
	detailEnvironment: DetailEnvironment
) : StatefulViewModel<Unit, Unit, Unit, IDetailEnvironment>(Unit, detailEnvironment) {

	override fun dispatch(action: Unit) {
		TODO("Not yet implemented")
	}

}