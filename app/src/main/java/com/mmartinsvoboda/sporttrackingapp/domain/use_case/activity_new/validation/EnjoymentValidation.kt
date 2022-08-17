package com.mmartinsvoboda.sporttrackingapp.domain.use_case.activity_new.validation

import com.mmartinsvoboda.sporttrackingapp.domain.use_case.ValidationResult
import javax.inject.Inject

class EnjoymentValidation @Inject constructor() {
    operator fun invoke(enjoyment: Int): ValidationResult {
        if (enjoyment in 0..10) {
            return ValidationResult(isSuccessful = true)
        }

        return ValidationResult(
            isSuccessful = false,
            error = "Enjoyment value must be a number between 0 and 10."
        )
    }
}