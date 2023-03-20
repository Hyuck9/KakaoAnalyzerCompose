package me.hyuck.kakaoanalyzer.features.statistics.time.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import me.hyuck.kakaoanalyzer.features.statistics.time.data.ITimeEnvironment
import me.hyuck.kakaoanalyzer.features.statistics.time.data.TimeEnvironment

@Module
@InstallIn(ViewModelComponent::class)
abstract class TimeModule {

	@Binds
	abstract fun provideEnvironment(
		environment: TimeEnvironment
	): ITimeEnvironment

}