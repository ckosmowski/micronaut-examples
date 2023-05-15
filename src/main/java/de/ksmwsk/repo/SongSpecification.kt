package de.ksmwsk.repo

import de.ksmwsk.model.SongEntity
import de.ksmwsk.model.SongQuery
import io.micronaut.data.runtime.criteria.where

object SongSpecification {
    fun matchesQuery(list: List<SongQuery>) = where<SongEntity> {
        or {
            for (request in list) {
                and {
                    root.get<String>("songHash") eq request.songHash
                }
            }
        }
    }

    fun matchesNestedQuery(list: List<SongQuery>) = where<SongEntity> {
        or {
            for (request in list) {
                and {
                    root.get<String>("artist.name") eq request.artist
                }
            }
        }
    }
}