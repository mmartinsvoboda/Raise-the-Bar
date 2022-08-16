package com.mmartinsvoboda.sporttrackingapp.domain.model

enum class SportType(
    val _name: String,
    val sportUnit: SportUnit
) {
    OutdoorRunning(
        "Outdoor Running",
        SportUnit.Distance
    ),
    Walking(
        "Walking",
        SportUnit.Distance
    ),
    Cycling(
        "Cycling",
        SportUnit.Distance
    ),
    RollerSkating(
        "Roller Skating",
        SportUnit.Distance
    ),
    Hiking(
        "Hiking",
        SportUnit.Distance
    ),
    Football(
        "Football",
        SportUnit.Goals
    ),
    Treadmill(
        "Treadmill",
        SportUnit.Distance
    )
}

enum class SportUnit(
    val _name: String,
    val unit: String
) {
    Distance(
        "Distance",
        "km"
    ),
    Goals(
        "Goals",
        "goals"
    )
}