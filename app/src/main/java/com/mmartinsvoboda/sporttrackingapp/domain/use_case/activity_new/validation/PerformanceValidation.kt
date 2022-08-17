package com.mmartinsvoboda.sporttrackingapp.domain.use_case.activity_new.validation

import androidx.core.text.isDigitsOnly
import com.mmartinsvoboda.sporttrackingapp.domain.use_case.ValidationResult
import javax.inject.Inject

class PerformanceValidation @Inject constructor() {
    operator fun invoke(performance: String): ValidationResult {
        val performanceAsNumber = performance.toIntOrNull()
        if (performance.isNotBlank() && performance.isDigitsOnly() && performanceAsNumber != null && performanceAsNumber > 0) {
            return ValidationResult(isSuccessful = true)
        }

        return ValidationResult(
            isSuccessful = false,
            error = "Performance must be a positive number."
        )
    }
}