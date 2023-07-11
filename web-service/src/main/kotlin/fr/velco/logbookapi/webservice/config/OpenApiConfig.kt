package fr.velco.logbookapi.webservice.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.annotations.security.SecuritySchemes
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.servers.Server
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@SecuritySchemes(
    value = [
        SecurityScheme(
            name = "bearerAuth",
            type = SecuritySchemeType.HTTP,
            bearerFormat = "JWT",
            scheme = "bearer",
        ),
        SecurityScheme(
            name = "x-api-key",
            type = SecuritySchemeType.APIKEY,
            `in` = SecuritySchemeIn.HEADER,
        )
    ]
)
@OpenAPIDefinition
class OpenApiConfig(
    @Value("\${velco.swagger.server-url}") private val serverUrl: String,
) {
    @Bean
    fun customOpenAPI(buildProperties: BuildProperties): OpenAPI? {
        val openAPI = OpenAPI()

        val server = Server()
        server.url = serverUrl

        val info = Info()
        info.title = "Logbook API"
        info.version = buildProperties.version

        openAPI.servers(listOf(server))
        openAPI.info(info)
        return openAPI
    }
}
