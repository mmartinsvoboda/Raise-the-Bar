package com.mmartinsvoboda.sporttrackingapp.data.manager

import com.mmartinsvoboda.sporttrackingapp.data.proto.repositories.user.ProtoUserRepo
import com.mmartinsvoboda.sporttrackingapp.domain.manager.UserManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class UserManagerImpl(
    private val protoUserRepo: ProtoUserRepo
) : UserManager {

    override suspend fun getUserName(): String {
        return protoUserRepo.getUserState().first()
    }

    override fun userNameFlow(): Flow<String> {
        return protoUserRepo.getUserState()
    }

    override fun isUserLoggedInFlow(): Flow<Boolean> {
        return protoUserRepo.getUserState().map { it.isNotBlank() }
    }

    override suspend fun loginUser(user: String) {
        protoUserRepo.saveUserState(user)
    }

    override suspend fun logOutUser() {
        protoUserRepo.saveUserState("")
    }
}