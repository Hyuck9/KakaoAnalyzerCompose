package me.hyuck.kakaoanalyzer.foundation.extension

import java.util.regex.Pattern

/** ex) 2019년 10월 24일 오전 10:44, */
private const val FIRST_DATE_TIME_COMMA_PATTERN = "^([2-9]\\d\\d\\d년)(\\s)([1-9]월|1[0-2]월)(\\s)([1-9]일|[1-3]\\d일)(\\s)(오전|오후)(\\s)([1-9]:|1[0-2]:)([1-9],|[0-5]\\d,)+.*"

/** ex) 2019년 10월 24일 오전 10:44 */
private const val FIRST_DATE_TIME_PATTERN = "^([2-9][0-9][0-9][0-9]년)(\\s)([1-9]월|1[0-2]월)(\\s)([1-9]일|[1-3][0-9]일)(\\s)(오전|오후)(\\s)([1-9]:|1[0-2]:)([1-9]|[0-5][0-9])+.*"

fun String.secondLastIndexOf(
    string: String,
    startIndex: Int = lastIndex,
    ignoreCase: Boolean = false
) =
    substring(0, this.lastIndexOf(string, startIndex, ignoreCase) - 1).lastIndexOf(
        string,
        startIndex,
        ignoreCase
    )

fun String.isFirstDateTimeMessage() = Pattern.matches(FIRST_DATE_TIME_COMMA_PATTERN, this)

fun String.isDateTimeMessage() = Pattern.matches(FIRST_DATE_TIME_PATTERN, this)
fun String.isNotDateTimeMessage() = this.isDateTimeMessage().not()