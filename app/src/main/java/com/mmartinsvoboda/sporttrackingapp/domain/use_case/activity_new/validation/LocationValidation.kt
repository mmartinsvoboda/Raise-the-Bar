package com.mmartinsvoboda.sporttrackingapp.domain.use_case.activity_new.validation

import com.mmartinsvoboda.sporttrackingapp.domain.use_case.ValidationResult
import javax.inject.Inject

class LocationValidation @Inject constructor() {
    operator fun invoke(location: String): ValidationResult {
        if (location.isBlank())
            return ValidationResult(isSuccessful = false, error = "Location can not be empty.")

        return ValidationResult(isSuccessful = true)
    }
}