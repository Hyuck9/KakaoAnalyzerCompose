package me.hyuck.kakaoanalyzer.foundation.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.hyuck.kakaoanalyzer.foundation.data.local.AppDatabase
import me.hyuck.kakaoanalyzer.foundation.data.local.dao.ChatDao
import me.hyuck.kakaoanalyzer.foundation.data.local.dao.MessageDao
import me.hyuck.kakaoanalyzer.foundation.data.local.dao.WordDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

	@Singleton
	@Provides
	fun provideChatDao(@ApplicationContext context: Context): ChatDao {
		return AppDatabase.getInstance(context).chatDao()
	}

	@Singleton
	@Provides
	fun provideMessageDao(@ApplicationContext context: Context): MessageDao {
		return AppDatabase.getInstance(context).messageDao()
	}

	@Singleton
	@Provides
	fun provideWordDao(@ApplicationContext context: Context): WordDao {
		return AppDatabase.getInstance(context).wordDao()
	}
}