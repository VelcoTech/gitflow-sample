package fr.velco.logbookapi.messaging.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.boot.autoconfigure.amqp.RabbitProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@EnableConfigurationProperties(AmqpConfigurationsProperties::class, RabbitProperties::class)
@Configuration
class AmqpLogbookEventListenerConfig(
    objectMapper: ObjectMapper,
    private val amqpConfigurationsProperties: AmqpConfigurationsProperties,
) : AmqpListenerConfigAbstract(objectMapper) {
    @Bean("logbookExchange")
    override fun exchange() = DirectExchange(amqpConfigurationsProperties.exchanges.logbook.exchangeName)

    @Bean("logbookEventDlq")
    override fun deadLetterQueue() =
        Queue(amqpConfigurationsProperties.exchanges.logbook.routingKeys.logbookEvent + AmqpConfig.DLQ_SUFFIX)

    @Bean("logbookEventConnectionFactory")
    override fun connectionFactory(): CachingConnectionFactory {
        val connectionFactory = CachingConnectionFactory(
            amqpConfigurationsProperties.exchanges.logbook.hostname,
            amqpConfigurationsProperties.exchanges.logbook.port
        )
        connectionFactory.virtualHost = amqpConfigurationsProperties.exchanges.logbook.virtualHost
        connectionFactory.username = amqpConfigurationsProperties.exchanges.logbook.username
        connectionFactory.setPassword(amqpConfigurationsProperties.exchanges.logbook.password)
        return connectionFactory
    }

    @Bean("bindingExchangeAndDlq")
    override fun exchangeDlqBinding() = super.exchangeDlqBinding()

    @Bean("logbookEventListenerConnectionFactory")
    override fun listenerContainerFactory(rabbitProperties: RabbitProperties) =
        super.listenerContainerFactory(rabbitProperties)

    @Bean("logbookEventListenerConnectionFactoryAdmin")
    override fun connectionFactoryAdmin() = super.connectionFactoryAdmin()
}
