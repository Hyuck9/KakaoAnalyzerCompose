package me.hyuck.kakaoanalyzer.foundation.extension

fun String.secondLastIndexOf(string: String, startIndex: Int = lastIndex, ignoreCase: Boolean = false) =
    substring(0, this.lastIndexOf(string, startIndex, ignoreCase)-1).lastIndexOf(string, startIndex, ignoreCase)