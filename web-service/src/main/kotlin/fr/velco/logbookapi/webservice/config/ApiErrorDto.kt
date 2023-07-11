package fr.velco.logbookapi.webservice.config

import java.time.LocalDateTime

class ApiErrorDto(
    val status: Int,
    val error: String,
    val errorCode: String?,
    val timestamp: LocalDateTime,
)
