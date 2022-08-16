package com.mmartinsvoboda.sporttrackingapp.data

import com.mmartinsvoboda.sporttrackingapp.data.local.entity.SportActivityEntity
import com.mmartinsvoboda.sporttrackingapp.data.remote.dto.get_activity_dto.Item
import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity
import java.time.LocalDateTime
import java.time.ZoneOffset

fun Item.toSportActivityEntity(): SportActivityEntity {
    return SportActivityEntity(
        endDateTime = end,
        name = name,
        place = place,
        startDateTime = start,
        user = user,
        isBackedUp = true,
        remoteId = id,
        description = description,
        enjoyment = enjoyment
    )
}

fun SportActivity.toSportActivityItem(user: String): Item {
    return Item(
        end = endDateTime.toEpochSecond(ZoneOffset.UTC),
        name = name,
        place = place,
        start = startDateTime.toEpochSecond(ZoneOffset.UTC),
        user = user,
        id = String(),
        enjoyment = enjoyment,
        description = description
    )
}

fun SportActivityEntity.toSportActivity(): SportActivity {
    return SportActivity(
        endDateTime = LocalDateTime.ofEpochSecond(
            endDateTime,
            0,
            ZoneOffset.UTC
        ),
        name = name,
        place = place,
        startDateTime = LocalDateTime.ofEpochSecond(
            startDateTime,
            0,
            ZoneOffset.UTC
        ),
        isBackedUp = isBackedUp,
        id = id,
        description = description,
        enjoyment = enjoyment
    )
}

fun SportActivity.toSportActivityEntity(user: String): SportActivityEntity {
    return SportActivityEntity(
        endDateTime = endDateTime.toEpochSecond(ZoneOffset.UTC),
        name = name,
        place = place,
        startDateTime = startDateTime.toEpochSecond(ZoneOffset.UTC),
        user = user,
        isBackedUp = true,
        remoteId = null,
        description = description,
        enjoyment = enjoyment,
        id = id
    )
}