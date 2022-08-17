package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Note
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mmartinsvoboda.sporttrackingapp.presentation.components.DisplayErrorTextOnError
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.ActivityNewEvent
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.ActivityNewState
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.ActivityNewViewModel

@Composable
fun DescriptionSection(state: ActivityNewState, model: ActivityNewViewModel) {
    OutlinedTextField(
        value = state.description,
        onValueChange = { model.onEvent(ActivityNewEvent.SetDescription(it)) },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Description") },
        trailingIcon = {
            Icon(
                imageVector = Icons.Outlined.Note, contentDescription = null
            )
        },
        singleLine = false,
        maxLines = 10,
        shape = RoundedCornerShape(12.dp),
        isError = !state.descriptionError.isNullOrBlank()
    )

    DisplayErrorTextOnError(error = state.descriptionError)
}