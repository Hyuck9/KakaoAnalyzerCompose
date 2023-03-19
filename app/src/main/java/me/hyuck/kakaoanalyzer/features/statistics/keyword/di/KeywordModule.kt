package me.hyuck.kakaoanalyzer.features.statistics.keyword.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import me.hyuck.kakaoanalyzer.features.statistics.keyword.data.IKeywordEnvironment
import me.hyuck.kakaoanalyzer.features.statistics.keyword.data.KeywordEnvironment

@Module
@InstallIn(ViewModelComponent::class)
abstract class KeywordModule {

	@Binds
	abstract fun provideEnvironment(
		environment: KeywordEnvironment
	): IKeywordEnvironment

}