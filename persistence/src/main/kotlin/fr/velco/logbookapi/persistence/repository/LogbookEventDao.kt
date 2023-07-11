package fr.velco.logbookapi.persistence.repository

import fr.velco.logbookapi.persistence.entity.LogbookEvent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LogbookEventDao : JpaRepository<LogbookEvent, Long>
