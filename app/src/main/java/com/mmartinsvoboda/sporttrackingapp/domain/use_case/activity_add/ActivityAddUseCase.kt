package com.mmartinsvoboda.sporttrackingapp.domain.use_case.activity_add

import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity
import com.mmartinsvoboda.sporttrackingapp.domain.repository.SportActivityRepository
import timber.log.Timber
import javax.inject.Inject

class ActivityAddUseCase @Inject constructor(
    private val sportActivityRepository: SportActivityRepository
) {

    suspend operator fun invoke(
        sportActivity: SportActivity
    ): Boolean {
        return try {
            sportActivityRepository.addSportActivityToLocal(sportActivity)
            true
        } catch (e: Exception) {
            Timber.e(e)
            false
        }
    }

}