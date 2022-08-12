package com.mmartinsvoboda.sporttrackingapp.domain.use_case.get_activity

import com.mmartinsvoboda.sporttrackingapp.common.data.Resource
import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity
import com.mmartinsvoboda.sporttrackingapp.domain.repository.SportActivityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetActivityUseCase @Inject constructor(
    private val sportActivityRepository: SportActivityRepository
) {

    operator fun invoke(
        id: Int,
        user: String,
        fetchFromRemote: Boolean
    ): Flow<Resource<SportActivity?>> {
        return sportActivityRepository.getSportActivity(id, user, fetchFromRemote)
    }

}