package exmaple.common.proto

import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import javax.servlet.ServletRequest
import javax.servlet.http.HttpServletRequest

fun ServletRequest.host(): String = "$scheme://$serverName:$serverPort"

fun HttpServletRequest.clientIp(): String? {

//    var ip = getHeader("X-Forwarded-For")

    var ip = getHeader("X-Forwarded-For")

    if (ip == null || ip.isEmpty() || ip.equals("unknown", ignoreCase = true)) {
        ip = getHeader("Proxy-Client-IP")
    }
    if (ip == null || ip.isEmpty() || ip.equals("unknown", ignoreCase = true)) {
        ip = getHeader("WL-Proxy-Client-IP")
    }
    if (ip == null || ip.isEmpty() || ip.equals("unknown", ignoreCase = true)) {
        ip = getHeader("HTTP_CLIENT_IP")
    }
    if (ip == null || ip.isEmpty() || ip.equals("unknown", ignoreCase = true)) {
        ip = getHeader("HTTP_X_FORWARDED_FOR")
    }
    if (ip == null || ip.isEmpty() || ip.equals("unknown", ignoreCase = true)) {
        ip = getHeader("X-Real-IP")
    }
    if (ip == null || ip.isEmpty() || ip.equals("unknown", ignoreCase = true)) {
        ip = getHeader("REMOTE_ADDR")
    }
    if (ip == null || ip.isEmpty() || ip.equals("unknown", ignoreCase = true)) {
        ip = remoteAddr
    }

    return ip
}

fun request(): HttpServletRequest = (RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes).request
