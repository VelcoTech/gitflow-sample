package fr.velco.logbookapi.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["fr.velco.logbookapi"])
class Application {
}

fun main() {
    runApplication<Application>() // Start SpringBoot
}
