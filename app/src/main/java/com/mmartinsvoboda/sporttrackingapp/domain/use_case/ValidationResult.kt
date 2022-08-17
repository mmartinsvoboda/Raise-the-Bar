package com.mmartinsvoboda.sporttrackingapp.domain.use_case

data class ValidationResult(
    val isSuccessful: Boolean = false,
    val error: String? = null
)
