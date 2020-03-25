package de.ksmwsk.introspected

import io.micronaut.core.annotation.Introspected
import io.micronaut.core.convert.format.Format
import java.time.LocalDateTime

@Introspected
data class MovieIntrospectedFormat(
        var name: String? = null,

        @param:Format("yyyy-MM-dd'T'HH:mm:ss")
        var publishingDate: LocalDateTime? = null
)