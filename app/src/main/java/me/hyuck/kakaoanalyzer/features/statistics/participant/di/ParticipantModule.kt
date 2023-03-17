package me.hyuck.kakaoanalyzer.features.statistics.participant.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import me.hyuck.kakaoanalyzer.features.statistics.participant.data.IParticipantEnvironment
import me.hyuck.kakaoanalyzer.features.statistics.participant.data.ParticipantEnvironment

@Module
@InstallIn(ViewModelComponent::class)
abstract class ParticipantModule {

	@Binds
	abstract fun provideEnvironment(
		environment: ParticipantEnvironment
	): IParticipantEnvironment

}