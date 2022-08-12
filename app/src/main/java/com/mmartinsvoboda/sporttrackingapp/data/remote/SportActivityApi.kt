package com.mmartinsvoboda.sporttrackingapp.data.remote

import com.mmartinsvoboda.sporttrackingapp.data.remote.dto.DeleteActivityDto
import com.mmartinsvoboda.sporttrackingapp.data.remote.dto.PutActivityDto
import com.mmartinsvoboda.sporttrackingapp.data.remote.dto.get_activity_dto.GetActivityDto
import retrofit2.Response
import retrofit2.http.*

interface SportActivityApi {

    @GET("activities/{user}")
    suspend fun getActivities(
        @Path("user") user: String
    ): Response<GetActivityDto>

    @GET("activity/{user}/{id}")
    suspend fun getActivity(
        @Path("user") user: String,
        @Path("id") id: String
    ): Response<GetActivityDto>

    @PUT("activity/{user}")
    suspend fun addActivity(
        @Path("user") user: String,
        @Body requestBody: String
    ): Response<PutActivityDto>

    @DELETE("activity/{user}/{id}")
    suspend fun deleteActivity(
        @Path("user") user: String,
        @Path("id") id: String
    ): Response<DeleteActivityDto>

}