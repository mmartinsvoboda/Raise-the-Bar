package com.mmartinsvoboda.sporttrackingapp.domain.use_case.activity_new.validation

import com.mmartinsvoboda.sporttrackingapp.domain.model.SportType
import com.mmartinsvoboda.sporttrackingapp.domain.use_case.ValidationResult
import javax.inject.Inject

class SportValidation @Inject constructor() {
    operator fun invoke(sportType: SportType?): ValidationResult {
        if (sportType == null)
            return ValidationResult(isSuccessful = false, error = "You have to select sport.")

        return ValidationResult(isSuccessful = true)
    }
}