package com.mmartinsvoboda.sporttrackingapp.domain.use_case.activity_sync_on

import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity
import com.mmartinsvoboda.sporttrackingapp.domain.repository.SportActivityRepository
import timber.log.Timber
import javax.inject.Inject

class ActivitySyncOnUseCase @Inject constructor(
    private val sportActivityRepository: SportActivityRepository
) {

    suspend operator fun invoke(
        user: String,
        sportActivity: SportActivity
    ): Boolean {
        return try {
            sportActivityRepository.addSportActivityToRemote(user, sportActivity)
        } catch (e: Exception) {
            Timber.e(e)
            false
        }
    }

}