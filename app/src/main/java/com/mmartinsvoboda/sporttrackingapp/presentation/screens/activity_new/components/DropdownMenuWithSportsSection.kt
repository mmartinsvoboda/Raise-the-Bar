package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mmartinsvoboda.sporttrackingapp.domain.model.SportType
import com.mmartinsvoboda.sporttrackingapp.presentation.components.DisplayErrorTextOnError
import com.mmartinsvoboda.sporttrackingapp.presentation.components.DropdownMenuSportApp
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.ActivityNewEvent
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.ActivityNewState
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_new.ActivityNewViewModel

@Composable
fun DropdownMenuWithSportsSection(state: ActivityNewState, model: ActivityNewViewModel) {
    DropdownMenuSportApp(
        items = SportType.values().sortedBy { it._name },
        itemLabel = { it._name },
        selectedItem = state.sport,
        searchBoxLabel = "Select a sport",
        isError = !state.sportError.isNullOrBlank(),
        modifier = Modifier.fillMaxWidth()
    ) {
        model.onEvent(ActivityNewEvent.SetSportType(it))
    }

    DisplayErrorTextOnError(state.sportError)
}