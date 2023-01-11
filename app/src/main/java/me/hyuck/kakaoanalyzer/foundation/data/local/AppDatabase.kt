package me.hyuck.kakaoanalyzer.foundation.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import me.hyuck.kakaoanalyzer.foundation.data.local.dao.ChatDao
import me.hyuck.kakaoanalyzer.foundation.data.local.dao.MessageDao
import me.hyuck.kakaoanalyzer.foundation.data.local.dao.WordDao

abstract class AppDatabase : RoomDatabase() {

	abstract fun chatDao(): ChatDao
	abstract fun messageDao(): MessageDao
	abstract fun wordDao(): WordDao

	companion object {
		private const val DATABASE_NAME = "KakaoAnalyzerCompose.db"

		@Volatile
		private var INSTANCE: AppDatabase? = null

		fun getInstance(context: Context): AppDatabase {
			return INSTANCE ?: synchronized(AppDatabase::class) {
				INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
			}
		}

		private fun buildDatabase(context: Context): AppDatabase =
			Room.databaseBuilder(
				context.applicationContext,
				AppDatabase::class.java,
				DATABASE_NAME
			)
				.fallbackToDestructiveMigration()
				.build()
	}
}