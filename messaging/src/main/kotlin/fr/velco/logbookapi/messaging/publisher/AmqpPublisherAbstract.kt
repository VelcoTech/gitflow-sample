package fr.velco.logbookapi.messaging.publisher

import com.fasterxml.jackson.databind.ObjectMapper
import fr.velco.logbookapi.messaging.config.ExceptionHandler
import jakarta.annotation.PostConstruct
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter

abstract class AmqpPublisherAbstract(
    private val objectMapper: ObjectMapper,
) {
    internal abstract val rabbitTemplate: RabbitTemplate

    internal abstract fun connectionFactory(): ConnectionFactory
    internal abstract fun sendMessage(message: Any, routingKey: String)

    @PostConstruct
    fun initRabbitTemplate() {
        rabbitTemplate.messageConverter = Jackson2JsonMessageConverter(objectMapper)
        rabbitTemplate.setReplyErrorHandler(ExceptionHandler())
    }
}
