package com.mmartinsvoboda.sporttrackingapp.domain.use_case.activity_new.validation

import com.mmartinsvoboda.sporttrackingapp.domain.use_case.ValidationResult
import java.time.LocalDateTime
import javax.inject.Inject

class StartDateTimeValidation @Inject constructor() {
    operator fun invoke(
        startDateTime: LocalDateTime?, endDateTime: LocalDateTime?
    ): ValidationResult {
        if (startDateTime == null) {
            return ValidationResult(isSuccessful = false, error = "Start time can not be empty.")
        }

        if (startDateTime == endDateTime) {
            return ValidationResult(
                isSuccessful = false, error = "Start time and End time can not be the same."
            )
        }

        if (endDateTime != null && startDateTime > endDateTime) {
            return ValidationResult(
                isSuccessful = false, error = "Start time can not be after the End time."
            )
        }

        return ValidationResult(isSuccessful = true)
    }
}