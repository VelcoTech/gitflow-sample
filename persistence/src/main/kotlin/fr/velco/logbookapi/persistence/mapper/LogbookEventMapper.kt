package fr.velco.logbookapi.persistence.mapper

import fr.velco.logbookapi.domain.dto.LogbookEventDto
import fr.velco.logbookapi.persistence.entity.LogbookEvent

class LogbookEventMapper {
    companion object {
        fun toDto(logbookEvent: LogbookEvent) = LogbookEventDto(
            id = logbookEvent.id,
        )
    }
}
