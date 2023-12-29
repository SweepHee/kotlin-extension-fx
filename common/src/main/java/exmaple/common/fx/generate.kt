package exmaple.common.fx

import exmaple.common.proto.prettyValue
import org.apache.commons.lang3.RandomStringUtils
import java.util.*


// A~Z, a~z, 0~9 랜덤스트링 만듦.
fun generateRandomString(length: Int = 16): String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}

fun generateAccountNumber(length: Int = 14): String {
    val random = Random()
    val randomNumber = StringBuilder()

    repeat(length) {
        randomNumber.append(random.nextInt(10))
    }
    return randomNumber.toString()
}

fun generateNumber(length: Int = 10): String = RandomStringUtils.randomNumeric(length)

fun generateRefId() = UUID.randomUUID().prettyValue()
fun generateGuid(length: Int = 16): String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}

fun generateArsNo(length: Int = 2): String {
    val allowedChars = ('0'..'9')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}

fun generateClientId() = generateGuid(32)

fun generateMemberCode(length: Int = 10) = "M" + RandomStringUtils.randomNumeric(length)

fun generateRequestId() = generateGuid(20)
