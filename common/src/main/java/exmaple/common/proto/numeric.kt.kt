package exmaple.common.proto

import java.math.RoundingMode
import java.text.DecimalFormat

// decimal format 사용시 반올림모드도 설정가능하게
fun DecimalFormat(pattern: String, mode: RoundingMode) = DecimalFormat(pattern).apply {
    this.roundingMode = mode
}