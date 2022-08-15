package com.mmartinsvoboda.sporttrackingapp.presentation.screens.login

data class LoginState(
    val user: String = "",
    val userError: String? = null,
    val isLoginInProgress: Boolean = false
)
