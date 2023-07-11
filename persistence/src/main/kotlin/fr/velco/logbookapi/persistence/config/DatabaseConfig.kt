package fr.velco.logbookapi.persistence.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(basePackages = ["fr.velco.logbookapi.persistence.repository"])
@EntityScan(basePackages = ["fr.velco.logbookapi.persistence.entity"])
class DatabaseConfig
