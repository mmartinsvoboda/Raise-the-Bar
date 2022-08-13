package com.mmartinsvoboda.sporttrackingapp.data.manager

import com.mmartinsvoboda.sporttrackingapp.data.proto.repositories.user.ProtoUserRepo
import com.mmartinsvoboda.sporttrackingapp.domain.manager.UserManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class UserManagerImpl(
    private val protoUserRepo: ProtoUserRepo
) : UserManager {

    override suspend fun getUserName(): String {
        return protoUserRepo.getUserState().first()
    }

    override fun userNameFlow(): Flow<String> {
        return protoUserRepo.getUserState().flowOn(Dispatchers.IO)
    }

    override fun isUserLoggedInFlow(): Flow<Boolean> {
        return protoUserRepo.getUserState().map { it.isNotBlank() }.flowOn(Dispatchers.IO)
    }

    override suspend fun loginUser(user: String) {
        protoUserRepo.saveUserState(user)
    }

    override suspend fun logOutUser() {
        protoUserRepo.saveUserState("")
    }
}