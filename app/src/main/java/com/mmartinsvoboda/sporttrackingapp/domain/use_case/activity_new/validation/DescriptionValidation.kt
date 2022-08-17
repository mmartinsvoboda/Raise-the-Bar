package com.mmartinsvoboda.sporttrackingapp.domain.use_case.activity_new.validation

import com.mmartinsvoboda.sporttrackingapp.domain.use_case.ValidationResult
import javax.inject.Inject

class DescriptionValidation @Inject constructor() {
    operator fun invoke(description: String): ValidationResult {
        if (description.isBlank())
            return ValidationResult(isSuccessful = false, error = "Description can not be empty.")

        return ValidationResult(isSuccessful = true)
    }
}