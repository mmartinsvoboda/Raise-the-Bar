package com.mmartinsvoboda.sporttrackingapp.presentation.screens.login

sealed class LoginEvent {
    object Login : LoginEvent()
    data class UpdateUser(val user: String) : LoginEvent()
}
