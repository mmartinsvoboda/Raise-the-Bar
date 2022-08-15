package com.mmartinsvoboda.sporttrackingapp.domain.use_case.user_login_validation

data class ValidationResult(
    val isSuccessful: Boolean = false, val error: String? = null
)
