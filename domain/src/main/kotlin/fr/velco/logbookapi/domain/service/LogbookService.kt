package fr.velco.logbookapi.domain.service

import fr.velco.logbookapi.domain.dto.LogbookEventDto
import fr.velco.logbookapi.domain.dto.LogbookEventMessageDto
import fr.velco.logbookapi.domain.port.LogbookEventPersistencePort
import org.springframework.stereotype.Service

@Service
class LogbookService(
    private val logbookEventPersistencePort: LogbookEventPersistencePort,
) {
    fun getLogbookEvents(): List<LogbookEventDto> = logbookEventPersistencePort.getAllLogbookEvents()

    fun treatLogbookEventMessage(logbookEventMessageDto: LogbookEventMessageDto) {
        // TODO: to implement
    }
}
