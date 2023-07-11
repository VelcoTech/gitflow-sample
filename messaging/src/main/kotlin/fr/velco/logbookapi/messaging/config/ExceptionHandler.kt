package fr.velco.logbookapi.messaging.config

import org.springframework.util.ErrorHandler

class ExceptionHandler : ErrorHandler {
    override fun handleError(exception: Throwable) {
        throw exception
    }
}
