package me.hyuck.kakaoanalyzer.runtime

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import me.hyuck.kakaoanalyzer.foundation.extension.fileCopy
import me.hyuck.kakaoanalyzer.foundation.extension.intentFor
import me.hyuck.kakaoanalyzer.foundation.extension.secondLastIndexOf
import timber.log.Timber

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
			val splashScreen = installSplashScreen()
			splashScreen.setKeepOnScreenCondition { true }
		}
		super.onCreate(savedInstanceState)

		/*if (storagePermissionCheck()) {
			startMainActivity()
		} else {
			requestPermission()
		}*/

	}

	override fun onResume() {
		super.onResume()

		when (intent?.action) {
			Intent.ACTION_SEND -> {
				intent.clipData?.getItemAt(0)?.uri?.let {
					copyToLocalFile(it)
				}
			}
		}
		startActivity(intentFor<MainActivity>())
		finish()
	}

	private fun copyToLocalFile(uri: Uri) {
		uri.path ?: return
		val targetPath = "$filesDir/${uri.path!!.substring(uri.path!!.secondLastIndexOf("/") + 1)}"
		contentResolver.openInputStream(uri)?.use { inputStream ->
			val isCopy = fileCopy(inputStream, targetPath)
			if (isCopy) {
				Timber.i("SUCCESS File Copy - $targetPath")
			} else {
				Timber.i("FAILURE File Copy - $targetPath")
			}
		}
	}

	/*private fun storagePermissionCheck(): Boolean = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
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
	}*/
}