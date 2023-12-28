package exmaple.common.proto

import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.createDirectories
import kotlin.io.path.isDirectory


fun startLine() = println("+---------------------------------------------+")
fun homeDir() = Path(System.getProperty("user.home"))

// 현재 경로 리턴
fun workDir() = Path(System.getProperty("user.dir"))

fun String.toFile(): File = File(this)
fun String.toPath(): Path = toFile().toPath()


// 파일 저장시 경로 지정. 존재하지 않으면 디렉토리 만듦
fun createPath(dir: String): Path = workDir().resolve(dir).run {
    if (!this.isDirectory()) {
        this.createDirectories()
    }
    return this
}


fun MultipartFile.toFile(source: String = ""): File {
    val path = source.ifBlank { originalFilename }
    val file = File(path)
    if (file.createNewFile()) {
        file.outputStream().write(this.bytes)
    }
    return file
}

fun mkdir(pathname: String, deleteIfExists: Boolean = false): File {
    val dir = File(pathname)
    if (dir.exists()) {
        if (deleteIfExists) {
            dir.listFiles()?.forEach { it.deleteRecursively() }
        }
    } else {
        dir.mkdirs()
    }

    return dir
}
