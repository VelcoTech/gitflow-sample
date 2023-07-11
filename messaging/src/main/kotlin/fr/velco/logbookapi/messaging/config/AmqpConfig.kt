package fr.velco.logbookapi.messaging.config

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration
import org.springframework.context.annotation.Configuration

@Configuration
@EnableRabbit
@EnableAutoConfiguration(exclude = [RabbitAutoConfiguration::class]) // Exclude auto-config because use specific config for each exchange
class AmqpConfig {
    companion object {
        const val DLQ_SUFFIX = "-dlq"
    }
}
