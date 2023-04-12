package me.hyuck.kakaoanalyzer.features.detail.keyword.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import me.hyuck.kakaoanalyzer.features.detail.data.DetailEnvironment
import me.hyuck.kakaoanalyzer.features.detail.data.IDetailEnvironment
import me.hyuck.kakaoanalyzer.features.detail.keyword.data.DetailKeywordEnvironment
import me.hyuck.kakaoanalyzer.features.detail.keyword.data.IDetailKeywordEnvironment

@Module
@InstallIn(ViewModelComponent::class)
abstract class DetailKeywordModule {

	@Binds
	abstract fun provideEnvironment(
		environment: DetailKeywordEnvironment
	): IDetailKeywordEnvironment

}