package com.mmartinsvoboda.sporttrackingapp.domain.use_case.get_activities_list

import com.mmartinsvoboda.sporttrackingapp.common.data.Resource
import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity
import com.mmartinsvoboda.sporttrackingapp.domain.repository.SportActivityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetActivitiesListUseCase @Inject constructor(
    private val sportActivityRepository: SportActivityRepository
) {

    operator fun invoke(
        user: String,
        fetchFromRemote: Boolean
    ): Flow<Resource<List<SportActivity>>> {
        return sportActivityRepository.getAllSportActivities(user, fetchFromRemote)
    }

}