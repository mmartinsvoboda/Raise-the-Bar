package com.mmartinsvoboda.sporttrackingapp.data.remote.dto


import com.google.gson.annotations.SerializedName

data class DeleteActivityDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("user")
    val user: String
)