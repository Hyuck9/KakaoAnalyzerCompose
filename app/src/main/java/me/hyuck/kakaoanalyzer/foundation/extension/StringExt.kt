package me.hyuck.kakaoanalyzer.foundation.extension

import java.util.*
import java.util.regex.Pattern

/** ex) 2019년 10월 24일 오전 10:44, */
private const val FIRST_DATE_TIME_COMMA_PATTERN = "^([2-9]\\d\\d\\d년)(\\s)([1-9]월|1[0-2]월)(\\s)([1-9]일|[1-3]\\d일)(\\s)(오전|오후)(\\s)([1-9]:|1[0-2]:)([1-9],|[0-5]\\d,)+.*"

/** ex) 2019년 10월 24일 오전 10:44 */
private const val FIRST_DATE_TIME_PATTERN = "^([2-9][0-9][0-9][0-9]년)(\\s)([1-9]월|1[0-2]월)(\\s)([1-9]일|[1-3][0-9]일)(\\s)(오전|오후)(\\s)([1-9]:|1[0-2]:)([1-9]|[0-5][0-9])+.*"

private const val IN_OUT_USER = "(.+?님이\\s.+?님을\\s초대했습니다.|.+?님이\\s나갔습니다.|.+?님을\\s내보냈습니다.|.+?님이\\s들어왔습니다.*|.+?된\\s메시지입니다.|.+?가\\s메시지를\\s가렸습니다.)"

private const val VOICE_TALK_TIME = "^보이스톡\\s([0-9]|[1-9][0-9]):[0-5][0-9]"
private const val VOICE_TALK_MESSAGE = "^보이스톡\\s.{2,3}"
private const val FACE_TALK_TIME = "^페이스톡\\s([0-9]|[1-9][0-9]):[0-5][0-9]"
private const val FACE_TALK_MESSAGE = "^페이스톡\\s.{2,3}"
private const val NOT_READ_MESSAGE = "^<.{2,3}\\s읽지\\s않음>"
private const val DELETED_MESSAGE = "^삭제된\\s메시지입니다."

private const val ATTACHED_FILE = "^파일:\\s(.*?doc|.*?docx|.*?hwp|.*?txt|.*?rtf|.*?xml|.*?pdf|.*?wks|.*?wps|.*?xps|.*?md|.*?odf|.*?odt|.*?ods|.*?odp|.*?csv|.*?tsv|.*?xls|.*?xlsx|.*?ppt|.*?pptx|.*?pages|.*?key|.*?numbers|.*?show|.*?ce|.*?zip|.*?gz|.*?bz2|.*?rar|.*?7z|.*?lzh|.*?alz)"
private const val IMAGE_FILE = "^\\w{64}(.jpg|.jpeg|.gif|.bmp|.png|.tif|.tiff|.tga|.psd|.ai)"
private const val MOVIE_FILE = "^\\w{64}(.mp4|.m4v|.avi|.asf|.wmv|.mkv|.ts|.mpg|.mpeg|.mov|.flv|.ogv)"
private const val MUSIC_FILE = "^\\w{64}(.mp3|.wav|.flac|.tta|.tak|.aac|.wma|.ogg|.m4a)"

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

fun String.isPassedInOutMessage() = Pattern.matches(IN_OUT_USER, this)

fun String.isPassedMessage() = matchPatterns(
    VOICE_TALK_TIME, VOICE_TALK_MESSAGE,
    FACE_TALK_TIME, FACE_TALK_MESSAGE,
    NOT_READ_MESSAGE, DELETED_MESSAGE,
    ATTACHED_FILE, IMAGE_FILE,
    MOVIE_FILE, MUSIC_FILE
)

fun String.replacePassedMessage() = when {
    Pattern.matches(ATTACHED_FILE, this) -> "<파일 전송>"
    Pattern.matches(IMAGE_FILE, this) -> "<사진 파일>"
    Pattern.matches(MOVIE_FILE, this) -> "<영상 파일>"
    Pattern.matches(MUSIC_FILE, this) -> "<음원 파일>"
    else -> this
}

fun String.isPassedKeyword(): Boolean {
    return this.isEmpty() ||
            this.equalsWords(":", ".", ",", "\"", "-", "\'", "[", "]") ||
            this.trim { it <= ' ' }.isEmpty() ||
            this.length > 20
}


fun String.containsWords(vararg words: String): Boolean {
    if (this.isEmpty()) return false
    words.forEach { word ->
        if (this.lowercase(Locale.getDefault()).contains(word.lowercase(Locale.getDefault()))) return true
    }
    return false
}

fun String.equalsWords(vararg words: String): Boolean {
    if (this.isEmpty()) return false
    words.forEach { word ->
        if (this == word) return true
    }
    return false
}

fun String.matchPatterns(vararg words: String): Boolean {
    if (this.isEmpty()) return false
    words.forEach { word ->
        if (Pattern.matches(word, this)) return true
    }
    return false
}