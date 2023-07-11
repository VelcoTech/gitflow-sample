# BROKER

## Description

Below are the list of all changes about the broker.

## Broker changes

- **0.0.1** version
    - RabbitMQ:
      - Create exchange "logbook" (virtual host = applications, type = direct, durability = durable)
      - Create user "logbook" with a new password
        - Add permission to the virtual host "applications"
