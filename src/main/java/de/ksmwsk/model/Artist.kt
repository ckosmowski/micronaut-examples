package de.ksmwsk.model

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class Artist(
    val name: String
)
