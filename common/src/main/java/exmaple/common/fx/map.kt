package exmaple.common.fx

import org.slf4j.MDC

object Mdc {
    fun put(key: String, value: String): Boolean {
        return try {
            MDC.put(key, value)
            true
        } catch (e: Exception) {
            false
        }
    }
    fun get(key: String): String {
        return try {
            MDC.get(key)
        } catch (e: Exception) {
            ""
        }
    }
    fun clear() {
        try {
            MDC.clear()
        } catch (e: Exception) {
            // ignore
        }
    }
}