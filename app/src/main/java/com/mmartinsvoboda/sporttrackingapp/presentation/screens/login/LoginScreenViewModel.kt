package com.mmartinsvoboda.sporttrackingapp.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmartinsvoboda.sporttrackingapp.domain.use_case.user_log_in.UserLogInUseCase
import com.mmartinsvoboda.sporttrackingapp.domain.use_case.user_login_validation.UserLoginValidationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val userLogInUseCase: UserLogInUseCase,
    private val userLoginValidationUseCase: UserLoginValidationUseCase
) : ViewModel() {

    private var _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.Login -> {
                viewModelScope.launch { logInUser() }
            }
            is LoginEvent.UpdateUser -> updateUser(event.user)
        }
    }

    private suspend fun logInUser() {
        val validationResult = userLoginValidationUseCase(user = state.value.user)

        if (validationResult.isSuccessful) {
            _state.value = state.value.copy(isLoginInProgress = true)
            userLogInUseCase(state.value.user)
            delay(750)

            viewModelScope.launch { validationEventChannel.send(ValidationEvent.Success) }
        }

        _state.value =
            state.value.copy(isLoginInProgress = false, userError = validationResult.error)
    }

    private fun updateUser(user: String) {
        if (!state.value.isLoginInProgress) _state.value = state.value.copy(user = user)
    }

}

sealed class ValidationEvent {
    object Success : ValidationEvent()
}