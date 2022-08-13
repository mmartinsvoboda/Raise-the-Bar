package com.mmartinsvoboda.sporttrackingapp.presentation.screens.login

sealed class LoginEvent {
    data class Login(val onLogin: () -> Unit) : LoginEvent()
    data class UpdateUser(val user: String) : LoginEvent()
}
