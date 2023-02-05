package me.hyuck.kakaoanalyzer.foundation.extension

import android.content.Context
import android.content.Intent

inline fun <reified T> Context.intentFor(config: Intent.() -> Unit = {}): Intent {
    return Intent(this, T::class.java).apply(config)
}
