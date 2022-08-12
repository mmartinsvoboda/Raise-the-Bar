package com.mmartinsvoboda.sporttrackingapp.domain.use_case.user_log_in

import com.mmartinsvoboda.sporttrackingapp.domain.manager.UserManager
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UserLogInUseCase @Inject constructor(
    private val userManager: UserManager
) {

    suspend operator fun invoke(
        user: String
    ): Boolean {
        if (userManager.isUserLoggedInFlow().first()) return false

        userManager.loginUser(user)
        return true
    }

}