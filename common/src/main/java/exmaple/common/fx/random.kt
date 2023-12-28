package exmaple.common.fx


// A~Z, a~z, 0~9 랜덤스트링 만듦.
fun generateRandomString(length: Int = 16): String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}
