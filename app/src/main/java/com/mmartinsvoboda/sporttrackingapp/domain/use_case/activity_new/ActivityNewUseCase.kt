package com.mmartinsvoboda.sporttrackingapp.domain.use_case.activity_new

import com.mmartinsvoboda.sporttrackingapp.domain.manager.UserManager
import com.mmartinsvoboda.sporttrackingapp.domain.model.SportActivity
import com.mmartinsvoboda.sporttrackingapp.domain.repository.SportActivityRepository
import kotlinx.coroutines.flow.first
import timber.log.Timber
import javax.inject.Inject

class ActivityNewUseCase @Inject constructor(
    private val sportActivityRepository: SportActivityRepository,
    private val userManager: UserManager
) {

    suspend operator fun invoke(
        sportActivity: SportActivity
    ): Boolean {
        return if (userManager.isUserLoggedInFlow().first()) {
            try {
                sportActivityRepository.addSportActivityToLocal(
                    userManager.getUserName(),
                    sportActivity
                )
                true
            } catch (e: Exception) {
                Timber.e(e)
                false
            }
        } else
            false
    }

}