package com.mmartinsvoboda.sporttrackingapp.data

import com.mmartinsvoboda.sporttrackingapp.data.local.entity.SportActivityEntity
import com.mmartinsvoboda.sporttrackingapp.data.remote.dto.get_activity_dto.Item
import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity

fun Item.toSportActivityEntity(): SportActivityEntity {
    return SportActivityEntity(
        end = end,
        name = name,
        place = place,
        start = start,
        user = user,
        isBackedUp = true,
        remoteId = id
    )
}

fun SportActivity.toSportActivityItem(): Item {
    return Item(
        end = end,
        name = name,
        place = place,
        start = start,
        user = user,
        id = String()
    )
}

fun SportActivityEntity.toSportActivity(): SportActivity {
    return SportActivity(
        end = end,
        name = name,
        place = place,
        start = start,
        user = user,
        isBackedUp = isBackedUp,
        id = id
    )
}

fun SportActivity.toSportActivityEntity(): SportActivityEntity {
    return SportActivityEntity(
        end = end,
        name = name,
        place = place,
        start = start,
        user = user,
        isBackedUp = true,
        remoteId = null
    )
}