package com.mmartinsvoboda.sporttrackingapp.domain.use_case.user_login_validation

import javax.inject.Inject

class UserLoginValidationUseCase @Inject constructor() {

    operator fun invoke(
        user: String
    ): ValidationResult {
        if (user.isBlank()) {
            return ValidationResult(
                isSuccessful = false,
                error = "Username can not be blank."
            )
        }

        if (user.length < 3) {
            return ValidationResult(
                isSuccessful = false,
                error = "Username must be at least 3 characters long."
            )
        }

        return ValidationResult(
            isSuccessful = true
        )
    }

}