package fr.velco.logbookapi.messaging.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "amqp-configurations")
class AmqpConfigurationsProperties(
    var exchanges: Exchanges,
) {
    class Exchanges(
        var logbook: Exchange,
    )

    class Exchange(
        var hostname: String,
        var port: Int,
        var virtualHost: String,
        var username: String,
        var password: String,
        var exchangeName: String,
        var routingKeys: RoutingKeys,
    )

    class RoutingKeys(
        var logbookEvent: String,
    )
}
