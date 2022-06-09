package de.ksmwsk.model

import io.micronaut.core.annotation.Introspected

@Introspected
data class SubClass (
    var name: String? = "bla"
)