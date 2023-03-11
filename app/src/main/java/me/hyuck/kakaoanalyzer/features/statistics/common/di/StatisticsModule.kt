package me.hyuck.kakaoanalyzer.features.statistics.common.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import me.hyuck.kakaoanalyzer.features.statistics.common.data.IStatisticsEnvironment
import me.hyuck.kakaoanalyzer.features.statistics.common.data.StatisticsEnvironment

@Module
@InstallIn(ViewModelComponent::class)
abstract class StatisticsModule {

	@Binds
	abstract fun provideEnvironment(
		environment: StatisticsEnvironment
	): IStatisticsEnvironment

}