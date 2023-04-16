package me.hyuck.kakaoanalyzer.features.detail.message.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import me.hyuck.kakaoanalyzer.features.detail.message.data.IMessageEnvironment
import me.hyuck.kakaoanalyzer.features.detail.message.data.MessageEnvironment

@Module
@InstallIn(ViewModelComponent::class)
abstract class MessageModule {

	@Binds
	abstract fun provideEnvironment(
		environment: MessageEnvironment
	): IMessageEnvironment

}