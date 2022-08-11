package com.mmartinsvoboda.sporttrackingapp.data.remote.dto.get_activity_dto


import com.google.gson.annotations.SerializedName

data class GetActivityDto(
    @SerializedName("Count")
    val count: Int,
    @SerializedName("Items")
    val items: List<Item>,
    @SerializedName("ScannedCount")
    val scannedCount: Int
)