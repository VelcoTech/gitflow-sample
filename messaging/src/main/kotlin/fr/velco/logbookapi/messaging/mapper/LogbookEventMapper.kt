package fr.velco.logbookapi.messaging.mapper

import fr.velco.logbookapi.messaging.message.LogbookEventMessage
import fr.velco.logbookapi.domain.dto.LogbookEventMessageDto

class LogbookEventMapper {
    companion object {
        fun toDto(logbookEventMessage: LogbookEventMessage) = LogbookEventMessageDto()
    }
}
