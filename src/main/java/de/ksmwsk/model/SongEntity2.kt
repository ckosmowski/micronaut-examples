package de.ksmwsk.model

import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.serde.annotation.Serdeable
import org.bson.types.ObjectId

@Serdeable
@MappedEntity("songs2")
data class SongEntity2 (
    @field:Id
    var id: ObjectId? = null,
    var name: String? = null
)