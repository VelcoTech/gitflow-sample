package fr.velco.logbookapi.domain.exception

class UnauthorizedException(
    message: String?,
    val logStackTrace: Boolean = true,
) : Exception(message)
