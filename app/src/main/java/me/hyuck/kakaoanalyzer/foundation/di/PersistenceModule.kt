package me.hyuck.kakaoanalyzer.foundation.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.hyuck.kakaoanalyzer.foundation.data.local.AppDatabase
import me.hyuck.kakaoanalyzer.foundation.data.local.dao.ChatDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

	@Singleton
	@Provides
	fun provideChatDao(@ApplicationContext context: Context): ChatDao {
		return AppDatabase.getInstance(context).chatDao()
	}
}