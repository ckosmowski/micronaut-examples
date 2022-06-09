package de.ksmwsk.model

import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity

@MappedEntity("songs")
data class SongEntity (
    @field:Id
    var songHash: String? = null,
    var name: String? = null,
    var subClass: SubClass? = null
)