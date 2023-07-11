package fr.velco.logbookapi.webservice.config

import fr.velco.logbookapi.domain.exception.UnauthorizedException
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.client.HttpClientErrorException.Unauthorized
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

private val log = KotlinLogging.logger {}

@ControllerAdvice
class ExceptionHandlerController : ResponseEntityExceptionHandler() {
    private fun getApiError(status: Int, error: String, errorCode: String? = null): ApiErrorDto = ApiErrorDto(
        status = status,
        error = error,
        errorCode = errorCode,
        timestamp = LocalDateTime.now(),
    )

    /**
     * UnauthorizedException = Error 401 (HttpStatus.UNAUTHORIZED)
     */
    @ExceptionHandler(UnauthorizedException::class)
    fun handleUnauthorized(e: UnauthorizedException): ResponseEntity<Any?>? {
        if (e.logStackTrace) {
            log.error("handleUnauthorized : $e")
        } else {
            log.error("handleUnauthorized : ${e.message}")
        }
        return ResponseEntity(
            getApiError(status = 401, error = e.message ?: "Unauthorized"),
            HttpStatus.UNAUTHORIZED,
        )
    }

    /**
     * UnauthorizedException = Error 401 (HttpStatus.UNAUTHORIZED)
     */
    @ExceptionHandler(Unauthorized::class) // From Spring Boot Security
    fun handleUnauthorized(e: Unauthorized): ResponseEntity<Any?>? {
        log.error("handleUnauthorized : $e")
        return ResponseEntity(
            getApiError(status = 401, error = e.message ?: "Unauthorized"),
            HttpStatus.UNAUTHORIZED,
        )
    }
}
