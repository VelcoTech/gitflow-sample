package fr.velco.logbookapi.webservice

import fr.velco.logbookapi.domain.dto.LogbookEventDto
import fr.velco.logbookapi.domain.service.LogbookService
import fr.velco.logbookapi.webservice.utils.PathConstants
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "Logbook events")
class LogbookEventsController(private val logbookService: LogbookService) {
    @GetMapping(PathConstants.PATH_V1 + PathConstants.LOGBOOK_EVENTS, produces = [MediaType.APPLICATION_JSON_VALUE])
    @Operation(
        summary = "Get logbook events",
        responses = [
            ApiResponse(responseCode = "200", description = "Ok"),
            ApiResponse(
                responseCode = "400",
                description = "Bad request. Check the request parameters",
                content = [Content()]
            ),
            ApiResponse(
                responseCode = "401",
                description = "Unauthorized. Check authentication",
                content = [Content()]
            ),
            ApiResponse(responseCode = "403", description = "Forbidden", content = [Content()]),
        ],
        security = [SecurityRequirement(name = "x-api-key"), SecurityRequirement(name = "bearerAuth")]
    )
    fun getLogbookEvents(): List<LogbookEventDto> = logbookService.getLogbookEvents()
}
