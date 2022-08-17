package com.mmartinsvoboda.sporttrackingapp.data

import com.mmartinsvoboda.sporttrackingapp.data.local.entity.SportActivityEntity
import com.mmartinsvoboda.sporttrackingapp.data.remote.dto.get_activity_dto.Item
import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity
import com.mmartinsvoboda.sporttrackingapp.domain.model.SportType
import java.time.LocalDateTime
import java.time.ZoneOffset

fun Item.toSportActivityEntity(): SportActivityEntity {
    return SportActivityEntity(
        endDateTime = end,
        sport = name,
        place = place,
        startDateTime = start,
        user = user,
        isBackedUp = true,
        remoteId = id,
        description = activity_description,
        enjoyment = enjoyment,
        performance = performance
    )
}

fun SportActivity.toSportActivityItem(user: String): Item {
    return Item(
        end = endDateTime.toEpochSecond(ZoneOffset.UTC),
        name = sport.name,
        place = place,
        start = startDateTime.toEpochSecond(ZoneOffset.UTC),
        user = user,
        id = String(),
        enjoyment = enjoyment,
        activity_description = description,
        performance = performance
    )
}

fun SportActivityEntity.toSportActivity(): SportActivity {
    return SportActivity(
        endDateTime = LocalDateTime.ofEpochSecond(
            endDateTime,
            0,
            ZoneOffset.UTC
        ),
        sport = SportType.valueOf(sport),
        place = place,
        startDateTime = LocalDateTime.ofEpochSecond(
            startDateTime,
            0,
            ZoneOffset.UTC
        ),
        isBackedUp = isBackedUp,
        id = id,
        description = description,
        enjoyment = enjoyment,
        performance = performance
    )
}

fun SportActivity.toSportActivityEntity(user: String): SportActivityEntity {
    return SportActivityEntity(
        endDateTime = endDateTime.toEpochSecond(ZoneOffset.UTC),
        sport = sport.name,
        place = place,
        startDateTime = startDateTime.toEpochSecond(ZoneOffset.UTC),
        user = user,
        isBackedUp = isBackedUp,
        remoteId = null,
        description = description,
        enjoyment = enjoyment,
        id = id,
        performance = performance
    )
}