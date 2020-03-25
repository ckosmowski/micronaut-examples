package de.ksmwsk.introspected

import io.micronaut.core.annotation.Introspected
import java.time.LocalDateTime

@Introspected
data class MovieIntrospected(
        var name: String? = null,
        var publishingDate: LocalDateTime? = null
)