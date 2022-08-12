package com.mmartinsvoboda.sporttrackingapp.domain.manager

import kotlinx.coroutines.flow.Flow

interface UserManager {

    suspend fun getUserName(): String
    fun userNameFlow(): Flow<String>
    fun isUserLoggedInFlow(): Flow<Boolean>
    suspend fun loginUser(user: String)
    suspend fun logOutUser()

}