package com.mmartinsvoboda.sporttrackingapp.data.remote

import okhttp3.RequestBody
import retrofit2.http.*

interface SportActivityApi {

    @GET("activities/{user}")
    suspend fun getActivities(
        @Path("user") user: String
    )

    @GET("activity/{user}/{id}")
    suspend fun getActivity(
        @Path("user") user: String,
        @Path("id") id: String
    )

    @PUT("activity/{user}")
    suspend fun addActivity(
        @Path("user") user: String,
        @Body requestBody: RequestBody
    )

    @DELETE("activity/{user}/{id}")
    suspend fun deleteActivity(
        @Path("user") user: String,
        @Path("id") id: String
    )

}