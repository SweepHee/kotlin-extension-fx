package exmaple.common.proto

import exmaple.common.ext.objectMapper
import java.text.DecimalFormat

// 10.0 같은 숫자 10으로 파싱. 엑셀업로드 같은 곳에서 유용
fun Any.decimalFormatToLong() = DecimalFormat("#").format(this.toString().toDouble()).toLong()
fun Any.decimalFormatToInt() = DecimalFormat("#").format(this.toString().toDouble()).toInt()

// json format
fun Any.toJson(): String = objectMapper.writeValueAsString(this)
// 보기편한 json format
fun Any.toPrettyJson(): String = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this)
