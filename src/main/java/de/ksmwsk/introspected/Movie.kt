package de.ksmwsk.introspected

import java.time.LocalDateTime

data class Movie(
        var name: String? = null,
        var publishingDate: LocalDateTime? = null
)