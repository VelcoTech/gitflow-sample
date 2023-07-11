package fr.velco.logbookapi.persistence.entity

import jakarta.persistence.*

@Entity
@Table(name = "logbook_event")
class LogbookEvent(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primary key auto generated
    @Column(name = "id_logbook_event", nullable = false)
    val id: Long = 0,
)
