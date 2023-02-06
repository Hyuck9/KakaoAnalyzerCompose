package me.hyuck.kakaoanalyzer.foundation.extension

fun Long.parseMemory(): String {
    return when {
        this@parseMemory < 1024 -> {
            "${this@parseMemory}byte"
        }
        this@parseMemory < 1024 * 1024 -> {
            "${this@parseMemory/1024}KB"
        }
        this@parseMemory < 1024 * 1024 * 1024 -> {
            "${this@parseMemory/(1024 * 1024)}MB"
        }
        else -> {
            "${this@parseMemory/(1024 * 1024 * 1024)}GB"
        }
    }
}