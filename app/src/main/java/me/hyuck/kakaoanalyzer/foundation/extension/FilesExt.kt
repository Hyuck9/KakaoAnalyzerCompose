package me.hyuck.kakaoanalyzer.foundation.extension

import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

fun fileCopy(inputStream: InputStream, targetPath: String): Boolean {
    return try {
        val target = Paths.get(targetPath)

        if (Files.notExists(target.parent)) {
            Files.createDirectories(target.parent)
        }

        Files.copy(inputStream, target, StandardCopyOption.REPLACE_EXISTING)
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
}