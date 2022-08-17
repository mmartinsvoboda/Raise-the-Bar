package com.mmartinsvoboda.sporttrackingapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize

@Composable
fun <T> DropdownMenuSportApp(
    items: List<T>,
    itemLabel: (item: T) -> String,
    selectedItem: T?,
    searchBoxLabel: String,
    isError: Boolean,
    modifier: Modifier = Modifier,
    onItemSelected: (item: T) -> Unit
) {

    // Declaring a boolean value to store
    // the expanded state of the Text Field
    var expanded by remember { mutableStateOf(false) }

    var textFieldSize by remember { mutableStateOf(androidx.compose.ui.geometry.Size.Zero) }

    // Up Icon when expanded and down icon when collapsed
    val icon = if (expanded) Icons.Filled.KeyboardArrowUp
    else Icons.Filled.KeyboardArrowDown

    Column(modifier) {

        // Create an Outlined Text Field
        // with icon and not expanded
        OutlinedTextField(
            value = if (selectedItem != null) itemLabel(selectedItem) else "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    // This value is used to assign to
                    // the DropDown the same width
                    textFieldSize = coordinates.size.toSize()
                },
            label = { Text(searchBoxLabel) },
            shape = RoundedCornerShape(12.dp),
            trailingIcon = {
                Icon(icon, "contentDescription", Modifier.clickable { expanded = !expanded })
            },
            readOnly = true,
            singleLine = true,
            interactionSource = remember { MutableInteractionSource() }.also { interactionSource ->
                LaunchedEffect(interactionSource) {
                    interactionSource.interactions.collect {
                        if (it is PressInteraction.Release) {
                            // works like onClick
                            expanded = !expanded
                        }
                    }
                }
            },
            isError = isError
        )

        // Create a drop-down menu with list of cities,
        // when clicked, set the Text Field text as the city selected
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            items.forEachIndexed { index, item ->
                Column {
                    DropdownMenuItem(
                        onClick = {
                            onItemSelected(item)
                            expanded = false
                        }, enabled = item != selectedItem
                    ) {
                        Text(text = itemLabel(item))
                    }

                    if (index != items.lastIndex) Divider()
                }
            }
        }
    }
}