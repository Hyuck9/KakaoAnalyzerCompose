package me.hyuck.kakaoanalyzer.features.statistics.time.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import me.hyuck.kakaoanalyzer.features.statistics.time.data.ITimeZoneEnvironment
import me.hyuck.kakaoanalyzer.features.statistics.time.data.TimeZoneEnvironment

@Module
@InstallIn(ViewModelComponent::class)
abstract class TimeZoneModule {

	@Binds
	abstract fun provideEnvironment(
		environment: TimeZoneEnvironment
	): ITimeZoneEnvironment

}