package com.mmartinsvoboda.sporttrackingapp.domain.use_case.user.user_log_out

import com.mmartinsvoboda.sporttrackingapp.domain.manager.UserManager
import javax.inject.Inject

class UserLogOutUseCase @Inject constructor(
    private val userManager: UserManager
) {

    suspend operator fun invoke(): Boolean {
        userManager.logOutUser()
        return true
    }

}