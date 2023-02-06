package me.hyuck.kakaoanalyzer.foundation.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import me.hyuck.kakaoanalyzer.foundation.data.local.converter.DateConverter
import me.hyuck.kakaoanalyzer.foundation.data.local.dao.ChatDao
import me.hyuck.kakaoanalyzer.foundation.data.local.dao.MessageDao
import me.hyuck.kakaoanalyzer.foundation.data.local.dao.WordDao
import me.hyuck.kakaoanalyzer.foundation.data.local.entity.ChatEntity
import me.hyuck.kakaoanalyzer.foundation.data.local.entity.MessageEntity
import me.hyuck.kakaoanalyzer.foundation.data.local.entity.WordEntity

@Database(
	entities = [ChatEntity::class, MessageEntity::class, WordEntity::class],
	version = 3,
	exportSchema = false
)
@TypeConverters(DateConverter::class)
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

		private val MIGRATION_1_2 = object : Migration(1, 2) {
			override fun migrate(database: SupportSQLiteDatabase) {
				database.execSQL("DROP TABLE chat_info")
				database.execSQL("DROP TABLE message_info")
				database.execSQL("DROP TABLE keyword_info")
			}
		}
	}
}