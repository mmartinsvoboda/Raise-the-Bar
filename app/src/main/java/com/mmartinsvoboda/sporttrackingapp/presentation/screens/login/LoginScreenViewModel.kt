package com.mmartinsvoboda.sporttrackingapp.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmartinsvoboda.sporttrackingapp.domain.use_case.user_log_in.UserLogInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val userLogInUseCase: UserLogInUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.Login -> {
                viewModelScope.launch {
                    if (logInUser()) event.onLogin()
                }
            }
            is LoginEvent.UpdateUser -> updateUser(event.user)
        }
    }

    private suspend fun logInUser(): Boolean {
        _state.value = state.value.copy(isLoginInProgress = true)
        val result = userLogInUseCase(state.value.user)
        delay(500)
        _state.value = state.value.copy(isLoginInProgress = false)

        return result
    }

    private fun updateUser(user: String) {
        if (!state.value.isLoginInProgress)
            _state.value = state.value.copy(user = user)
    }

}