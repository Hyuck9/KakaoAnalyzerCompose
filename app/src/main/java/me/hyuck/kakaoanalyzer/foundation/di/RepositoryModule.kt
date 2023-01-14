package me.hyuck.kakaoanalyzer.foundation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import me.hyuck.kakaoanalyzer.foundation.data.local.dao.ChatDao
import me.hyuck.kakaoanalyzer.foundation.data.repository.ChatRepository
import me.hyuck.kakaoanalyzer.foundation.data.repository.ChatRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

	@Provides
	@Singleton
	fun provideChatRepository(
		chatDao: ChatDao,
		coroutineDispatcher: CoroutineDispatcher
	): ChatRepository = ChatRepositoryImpl(chatDao, coroutineDispatcher)

}