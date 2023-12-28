package exmaple.common.proto

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

// datetime to offsetDatetime
fun LocalDateTime.toOffsetDateTime(): OffsetDateTime = this.atOffset(ZoneId.of("Asia/Seoul").rules.getOffset(this))

// 날짜 사이에 일자 구함
fun LocalDate.durationDaysUntil(other: LocalDate): Long = ChronoUnit.DAYS.between(other, this)


fun LocalDateTime.formatString(pattern: String = "yyyy-MM-dd HH:mm:ss"): String = format(DateTimeFormatter.ofPattern(pattern))
fun LocalDate.formatString(pattern: String = "yyyy-MM-dd"): String = format(DateTimeFormatter.ofPattern(pattern))
fun LocalTime.formatString(pattern: String = "HH:mm"): String = format(DateTimeFormatter.ofPattern(pattern))