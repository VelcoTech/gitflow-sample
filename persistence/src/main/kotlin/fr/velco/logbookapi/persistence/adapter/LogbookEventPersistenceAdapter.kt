package fr.velco.logbookapi.persistence.adapter

import fr.velco.logbookapi.domain.dto.LogbookEventDto
import fr.velco.logbookapi.persistence.mapper.LogbookEventMapper
import fr.velco.logbookapi.domain.port.LogbookEventPersistencePort
import fr.velco.logbookapi.persistence.repository.LogbookEventDao
import org.springframework.stereotype.Component

@Component
class LogbookEventPersistenceAdapter(
    private val logbookEventDao: LogbookEventDao,
) : LogbookEventPersistencePort {
    override fun getAllLogbookEvents(): List<LogbookEventDto> {
        val logbookEvents = logbookEventDao.findAll()
        return logbookEvents.map { LogbookEventMapper.toDto(it) }
    }
}
