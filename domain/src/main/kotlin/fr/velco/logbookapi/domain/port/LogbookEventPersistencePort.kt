package fr.velco.logbookapi.domain.port

import fr.velco.logbookapi.domain.dto.LogbookEventDto

interface LogbookEventPersistencePort {
    fun getAllLogbookEvents(): List<LogbookEventDto>
}
