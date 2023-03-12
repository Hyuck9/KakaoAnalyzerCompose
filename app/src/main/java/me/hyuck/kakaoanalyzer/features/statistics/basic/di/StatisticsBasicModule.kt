package me.hyuck.kakaoanalyzer.features.statistics.basic.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import me.hyuck.kakaoanalyzer.features.statistics.basic.data.IStatisticsBasicEnvironment
import me.hyuck.kakaoanalyzer.features.statistics.basic.data.StatisticsBasicEnvironment

@Module
@InstallIn(ViewModelComponent::class)
abstract class StatisticsBasicModule {

	@Binds
	abstract fun provideEnvironment(
		environment: StatisticsBasicEnvironment
	): IStatisticsBasicEnvironment

}