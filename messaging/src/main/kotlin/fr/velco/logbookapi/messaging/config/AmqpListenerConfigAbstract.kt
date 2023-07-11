package fr.velco.logbookapi.messaging.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.boot.autoconfigure.amqp.RabbitProperties
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer

abstract class AmqpListenerConfigAbstract(
    private val objectMapper: ObjectMapper,
) {
    internal abstract fun exchange(): DirectExchange
    internal abstract fun deadLetterQueue(): Queue
    internal abstract fun connectionFactory(): CachingConnectionFactory

    // To override with @Bean
    open fun exchangeDlqBinding(): Binding {
        return BindingBuilder.bind(deadLetterQueue()).to(exchange()).with(deadLetterQueue().name)
    }

    // To override with @Bean
    open fun listenerContainerFactory(rabbitProperties: RabbitProperties): SimpleRabbitListenerContainerFactory {
        val configurer = SimpleRabbitListenerContainerFactoryConfigurer(rabbitProperties)

        val factory = SimpleRabbitListenerContainerFactory()
        configurer.configure(factory, connectionFactory())
        factory.setMessageConverter(Jackson2JsonMessageConverter(objectMapper))
        factory.setErrorHandler(ExceptionHandler())
        return factory
    }

    // To override with @Bean
    open fun connectionFactoryAdmin(): AmqpAdmin {
        return RabbitAdmin(connectionFactory())
    }
}
