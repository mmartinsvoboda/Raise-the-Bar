package com.mmartinsvoboda.sporttrackingapp.domain.use_case.get_activities_list

import com.mmartinsvoboda.sporttrackingapp.common.data.Resource
import com.mmartinsvoboda.sporttrackingapp.domain.manager.UserManager
import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity
import com.mmartinsvoboda.sporttrackingapp.domain.repository.SportActivityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetActivitiesListUseCase @Inject constructor(
    private val sportActivityRepository: SportActivityRepository,
    private val userManager: UserManager
) {

    suspend operator fun invoke(
        fetchFromRemote: Boolean
    ): Flow<Resource<List<SportActivity>>>? {
        return if (userManager.isUserLoggedInFlow().first()) {
            sportActivityRepository.getAllSportActivities(
                userManager.getUserName(),
                fetchFromRemote
            )
        } else null
    }

}