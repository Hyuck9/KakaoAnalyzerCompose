package me.hyuck.kakaoanalyzer.runtime

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.OpenableColumns
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import me.hyuck.kakaoanalyzer.foundation.extension.intentFor
import timber.log.Timber

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
			val splashScreen = installSplashScreen()
			splashScreen.setKeepOnScreenCondition { true }
		}

		super.onCreate(savedInstanceState)

		if (storagePermissionCheck()) {
			startMainActivity()
		} else {
			requestPermission()
		}

	}

	override fun onResume() {
		super.onResume()

		when (intent?.action) {
			Intent.ACTION_SEND -> {
				Timber.tag("TEST").i("ACTION_SEND, ${intent.type}")
				Timber.tag("TEST").i("ACTION_SEND, ${intent.clipData}")
				Timber.tag("TEST").i("ACTION_SEND, ${intent.clipData?.getItemAt(0)}")
				Timber.tag("TEST").i("ACTION_SEND, ${intent.clipData?.getItemAt(0)?.uri}")
				intent.clipData?.getItemAt(0)?.uri?.let {
					contentResolver.query(it, null, null, null, null)
				}?.use { cursor ->
					val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
					val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
					cursor.moveToFirst()
					Timber.tag("TEST").i("nameIndex : $nameIndex, ${cursor.getString(nameIndex)}")
					Timber.tag("TEST").i("sizeIndex : $sizeIndex, ${cursor.getLong(sizeIndex)}")
				}
			}
			else -> {
				Timber.tag("TEST").i("ELSE!!!!!")
			}
		}
	}

	private fun startMainActivity() = startActivity(
		intentFor<MainActivity> {
			flags = Intent.FLAG_ACTIVITY_NEW_TASK or
					Intent.FLAG_ACTIVITY_CLEAR_TASK or
					Intent.FLAG_ACTIVITY_CLEAR_TOP
		}
	)

	private fun storagePermissionCheck(): Boolean = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
		checkSelfPermission(Manifest.permission.READ_MEDIA_AUDIO) == PackageManager.PERMISSION_GRANTED &&
		checkSelfPermission(Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED &&
		checkSelfPermission(Manifest.permission.READ_MEDIA_VIDEO) == PackageManager.PERMISSION_GRANTED
	} else {
		checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
	}

	private fun requestPermission() {
		val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
			arrayOf(
				Manifest.permission.READ_MEDIA_AUDIO,
				Manifest.permission.READ_MEDIA_IMAGES,
				Manifest.permission.READ_MEDIA_VIDEO
			)
		} else {
			arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
		}
		TedPermission.create()
			.setPermissionListener(object : PermissionListener {
				override fun onPermissionGranted() {
					startMainActivity()
				}

				override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
					finish()
				}
			})
			.setDeniedMessage("권한을 거부하면 서비스를 이용할 수 없습니다.\n\n[설정] > [권한] 에서 권한을 허용해주세요.")
			.setPermissions(*permissions)
			.check()
	}
}