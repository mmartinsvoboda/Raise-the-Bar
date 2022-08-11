package com.mmartinsvoboda.sporttrackingapp.data.remote.dto.get_activity_dto


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("end") val end: String,
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("place") val place: String,
    @SerializedName("start") val start: String,
    @SerializedName("user") val user: String
)