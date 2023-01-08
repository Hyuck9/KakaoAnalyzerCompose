package me.hyuck.kakaoanalyzer.runtime

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import me.hyuck.kakaoanalyzer.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

	override fun onCreate() {
		super.onCreate()

		if (BuildConfig.DEBUG) {
			Timber.plant(Timber.DebugTree())
			Timber.d("Timber is initialized.")
		}

	}
}