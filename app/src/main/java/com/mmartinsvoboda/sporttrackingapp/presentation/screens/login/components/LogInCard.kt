package com.mmartinsvoboda.sporttrackingapp.presentation.screens.login.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mmartinsvoboda.sporttrackingapp.presentation.components.ButtonText
import com.mmartinsvoboda.sporttrackingapp.presentation.components.CardSportAppWithTitle
import com.mmartinsvoboda.sporttrackingapp.presentation.components.DisplayErrorTextOnError
import com.mmartinsvoboda.sporttrackingapp.presentation.components.SpacerDefault
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.login.LoginEvent
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.login.LoginScreenViewModel
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.login.LoginState
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.SportTrackingAppTheme

@Composable
fun LogInCard(
    state: LoginState, model: LoginScreenViewModel
) {
    CardSportAppWithTitle(
        title = "Log in",
        modifier = Modifier.padding(horizontal = SportTrackingAppTheme.paddings.defaultPadding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(SportTrackingAppTheme.paddings.defaultPadding)
        ) {
            OutlinedTextField(
                value = state.user,
                onValueChange = {
                    model.onEvent(LoginEvent.UpdateUser(it))
                },
                isError = !state.userError.isNullOrBlank(),
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Username")
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                enabled = !state.isLoginInProgress
            )

            DisplayErrorTextOnError(state.userError)

            SpacerDefault()

            Button(
                onClick = {
                    model.onEvent(LoginEvent.Login)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                enabled = !state.isLoginInProgress
            ) {
                ButtonText(text = "Log in")
            }

            OutlinedButton(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                enabled = !state.isLoginInProgress
            ) {
                ButtonText(text = "Sign up")
            }
        }
    }
}