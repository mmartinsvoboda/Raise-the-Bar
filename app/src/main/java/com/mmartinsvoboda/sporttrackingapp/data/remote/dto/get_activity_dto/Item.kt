package com.mmartinsvoboda.sporttrackingapp.data.remote.dto.get_activity_dto


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("end") val end: Long,
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("place") val place: String,
    @SerializedName("start") val start: Long,
    @SerializedName("user") val user: String,
    @SerializedName("activity_description") val description: String,
    @SerializedName("enjoyment") val enjoyment: Int
)