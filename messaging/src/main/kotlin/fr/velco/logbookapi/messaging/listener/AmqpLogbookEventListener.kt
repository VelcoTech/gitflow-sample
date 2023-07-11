package fr.velco.logbookapi.messaging.listener

import fr.velco.logbookapi.domain.service.LogbookService
import fr.velco.logbookapi.messaging.config.AmqpConfig
import fr.velco.logbookapi.messaging.mapper.LogbookEventMapper
import fr.velco.logbookapi.messaging.message.LogbookEventMessage
import mu.KotlinLogging
import org.springframework.amqp.core.ExchangeTypes
import org.springframework.amqp.rabbit.annotation.*
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {}

@Component
class AmqpLogbookEventListener(
    private val logbookService: LogbookService,
) {
    @RabbitListener(
        containerFactory = "logbookEventListenerConnectionFactory",
        bindings = [QueueBinding(
            value = Queue(
                value = "\${amqp-configurations.exchanges.logbook.routing-keys.logbook-event}",
                durable = "true",
                admins = ["logbookEventListenerConnectionFactoryAdmin"],
                arguments = [
                    Argument(
                        name = "x-dead-letter-exchange",
                        value = "\${amqp-configurations.exchanges.logbook.exchange-name}"
                    ),
                    Argument(
                        name = "x-dead-letter-routing-key",
                        value = "\${amqp-configurations.exchanges.logbook.routing-keys.logbook-event}" + AmqpConfig.DLQ_SUFFIX
                    )],
                ignoreDeclarationExceptions = "true"
            ),
            exchange = Exchange(
                value = "\${amqp-configurations.exchanges.logbook.exchange-name}",
                type = ExchangeTypes.DIRECT,
                ignoreDeclarationExceptions = "true"
            ),
            key = ["\${amqp-configurations.exchanges.logbook.routing-keys.logbook-event}"]
        )]
    )
    fun treatMessage(message: LogbookEventMessage) {
        log.debug("Treating message from logbook-event: $message")

        logbookService.treatLogbookEventMessage(LogbookEventMapper.toDto(message))
    }
}
