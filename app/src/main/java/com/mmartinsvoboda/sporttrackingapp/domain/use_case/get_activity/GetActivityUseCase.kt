package com.mmartinsvoboda.sporttrackingapp.domain.use_case.get_activity

import com.mmartinsvoboda.sporttrackingapp.common.data.Resource
import com.mmartinsvoboda.sporttrackingapp.domain.manager.UserManager
import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity
import com.mmartinsvoboda.sporttrackingapp.domain.repository.SportActivityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetActivityUseCase @Inject constructor(
    private val sportActivityRepository: SportActivityRepository,
    private val userManager: UserManager
) {

    suspend operator fun invoke(
        id: Int,
        fetchFromRemote: Boolean
    ): Flow<Resource<SportActivity?>>? {
        return if (userManager.isUserLoggedInFlow().first()) {
            sportActivityRepository.getSportActivity(
                id,
                userManager.getUserName(),
                fetchFromRemote
            )
        } else null
    }

}