package com.mmartinsvoboda.sporttrackingapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun ScaffoldSportApp(
    modifier: Modifier = Modifier,
    topBarTitle: String,
    topBarActions: @Composable RowScope.() -> Unit = {},
    topBarDisplayNavigationIcon: Boolean,
    floatingActionButton: @Composable () -> Unit = {},
    drawerContent: @Composable (ColumnScope.() -> Unit)? = null,
    snackBarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    displayTopAppBar: Boolean = true,
    navigator: DestinationsNavigator,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    content: @Composable (scaffoldState: ScaffoldState) -> Unit
) {
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            if (displayTopAppBar)
                TopAppBarSportApp(
                    title = {
                        TopBarText(text = topBarTitle)
                    },
                    actions = {
                        topBarActions()
                    },
                    displayNavigationIcon = topBarDisplayNavigationIcon,
                    navigationIcon = { NavigationIconTopAppBarSportApp(navigator) }
                )
        },
        floatingActionButton = floatingActionButton,
        drawerContent = drawerContent,
        snackbarHost = snackBarHost,
        modifier = modifier
    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            content(scaffoldState)
        }
    }
}