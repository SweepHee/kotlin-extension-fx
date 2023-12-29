package exmaple.common.proto

import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

// dd-MM-yyyy로 들어오는 날짜 yyyy-MM-dd로 변경
fun String.dmyToYmd (): String? = this.let {
    var date = it
    if (date.contains("월")) date = date.replace("월", "")
    if (date.contains("일")) date = date.replace("일", "")
    val form1 = SimpleDateFormat("dd-MM-yyyy")
    val form2 = SimpleDateFormat("yyyy-MM-dd")
    return form2.format(form1.parse(date))
}

// Sun Oct 29 00:00:00 KST 2023 -> yyyy-MM-dd
fun String.usLocaleDateToYmd(): String? = LocalDateTime.parse(
    this, DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US)
).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))


