package com.mmartinsvoboda.sporttrackingapp.domain.use_case.activity_new.validation

import com.mmartinsvoboda.sporttrackingapp.domain.use_case.ValidationResult
import java.time.LocalDateTime
import javax.inject.Inject

class EndDateTimeValidation @Inject constructor() {
    operator fun invoke(
        startDateTime: LocalDateTime?, endDateTime: LocalDateTime?
    ): ValidationResult {
        if (endDateTime == null) {
            return ValidationResult(isSuccessful = false, error = "End time can not be empty.")
        }

        if (startDateTime == endDateTime) {
            return ValidationResult(
                isSuccessful = false, error = "End time and Start time can not be the same."
            )
        }

        if (startDateTime != null && endDateTime < startDateTime) {
            return ValidationResult(
                isSuccessful = false, error = "End time can not be before the Start time."
            )
        }

        return ValidationResult(isSuccessful = true)
    }
}