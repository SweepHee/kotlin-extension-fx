package exmaple.common.proto

import java.io.File
import java.io.IOException
import java.io.PrintWriter
import kotlin.io.path.isReadable
import kotlin.io.path.readText

// 텍스트파일만들고 직접 내용넣을 수 있음 사용: textWriter(dir, filename).use { it.append("내용") }
fun textWriter(dir: String, filename: String, overwrite: Boolean = false): PrintWriter? {
    try {
        val path = createPath(dir).resolve(filename)
        if (!overwrite && path.isReadable()) return null
        return File(path.toString()).printWriter()
    } catch(e: Exception) {
        throw IOException("textWriter 파일쓰기 오류")
    }
}


// text 파일 만들고 내용넣기 중복이면 덮어씀
fun textOverWrite(dir: String, filename: String, text: String) {
    try {
        val path = createPath(dir).resolve(filename)
        File(path.toString()).printWriter().use { it.write(text) }
    } catch(e: Exception) {
        throw IOException("text 파일쓰기 오류")
    }
}

// text 파일 만들고 내용넣기 중복이면 건너뛰기
fun textSkipWrite(dir: String, filename: String, text: String) {
    try {
        run {
            val path = createPath(dir).resolve(filename)
            if (path.isReadable()) return@run
            File(path.toString()).printWriter().use { it.write(text) }
        }
    } catch(e: Exception) {
        throw IOException("text 파일쓰기 오류")
    }
}

// text 파일 존재하면 글 내용 추가하기
fun textAppendWrite(dir: String, filename: String, text: String) {
    try {
        val path = createPath(dir).resolve(filename)
        val appendText = if (path.isReadable()) path.readText() + text else text
        File(path.toString()).printWriter().use { it.write(appendText) }
    } catch(e: Exception) {
        throw IOException("text 파일쓰기 오류")
    }
}