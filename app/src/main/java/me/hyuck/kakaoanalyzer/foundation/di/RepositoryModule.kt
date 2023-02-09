package me.hyuck.kakaoanalyzer.foundation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import me.hyuck.kakaoanalyzer.foundation.data.local.dao.ChatDao
import me.hyuck.kakaoanalyzer.foundation.data.local.dao.MessageDao
import me.hyuck.kakaoanalyzer.foundation.data.local.dao.WordDao
import me.hyuck.kakaoanalyzer.foundation.data.repository.chat.ChatRepository
import me.hyuck.kakaoanalyzer.foundation.data.repository.chat.ChatRepositoryImpl
import me.hyuck.kakaoanalyzer.foundation.data.repository.message.MessageRepository
import me.hyuck.kakaoanalyzer.foundation.data.repository.message.MessageRepositoryImpl
import me.hyuck.kakaoanalyzer.foundation.data.repository.word.WordRepository
import me.hyuck.kakaoanalyzer.foundation.data.repository.word.WordRepositoryImpl
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

	@Provides
	@Singleton
	fun provideMessageRepository(
		messageDao: MessageDao,
		coroutineDispatcher: CoroutineDispatcher
	): MessageRepository = MessageRepositoryImpl(messageDao, coroutineDispatcher)

	@Provides
	@Singleton
	fun provideWordRepository(
		wordDao: WordDao,
		coroutineDispatcher: CoroutineDispatcher
	): WordRepository = WordRepositoryImpl(wordDao, coroutineDispatcher)

}