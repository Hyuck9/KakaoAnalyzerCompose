package me.hyuck.kakaoanalyzer.features.detail.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import me.hyuck.kakaoanalyzer.features.detail.data.DetailEnvironment
import me.hyuck.kakaoanalyzer.features.detail.data.IDetailEnvironment

@Module
@InstallIn(ViewModelComponent::class)
abstract class DetailModule {

	@Binds
	abstract fun provideEnvironment(
		environment: DetailEnvironment
	): IDetailEnvironment

}