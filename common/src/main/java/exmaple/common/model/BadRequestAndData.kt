package exmaple.common.model

import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
class BadRequestAndData(message: String? = null, var data: Any? = null, cause: Throwable? = null) : RuntimeException(message, cause)

@ResponseStatus(code = HttpStatus.CONFLICT)
class ConflictOccurs(message: String? = null, var data: Any? = null, cause: Throwable? = null) : RuntimeException(message, cause)

@ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE)
class ServiceUnavailable(message: String? = null, var data: Any? = null, cause: Throwable? = null) : RuntimeException(message, cause)
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
class BadRequest(message: String? = null, var data: Any? = null, cause: Throwable? = null) : RuntimeException(message, cause)

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
class ValidBadRequest(message: String? = null, var data: Any? = null, cause: Throwable? = null) : RuntimeException(message, cause)
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
class ServiceError(message: String? = null, var data: Any? = null, cause: Throwable? = null) : RuntimeException(message, cause)

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
class Unauthorized(message: String? = null, var data: Any? = null, cause: Throwable? = null) : RuntimeException(message, cause)
class ShellException(val commands: List<String>, e: Exception) : RuntimeException(e.localizedMessage, e.cause)
fun throwValidBadRequest(bindingResult: BindingResult) {
    throw ValidBadRequest(
        data = bindingResult
            .fieldErrors
            .map { it.field to it.defaultMessage }
            .map {
                object {
                    val field = it.first
                    val message = it.second
                }
            }
            .associateBy({ it.field }) { it.message }
    )
}
