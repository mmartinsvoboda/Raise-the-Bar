package com.mmartinsvoboda.sporttrackingapp.domain.use_case.activity_remove

import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity
import com.mmartinsvoboda.sporttrackingapp.domain.repository.SportActivityRepository
import timber.log.Timber
import javax.inject.Inject

class ActivityRemoveUseCase @Inject constructor(
    private val sportActivityRepository: SportActivityRepository
) {

    suspend operator fun invoke(
        user: String,
        sportActivity: SportActivity
    ): Boolean {
        return try {
            sportActivityRepository.deleteSportActivityFromRemote(sportActivity.id, user)
            sportActivityRepository.deleteSportActivityFromLocal(sportActivity)
            true
        } catch (e: Exception) {
            Timber.e(e)
            false
        }
    }

}