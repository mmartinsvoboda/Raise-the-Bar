package com.mmartinsvoboda.sporttrackingapp.presentation.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mmartinsvoboda.sporttrackingapp.R
import com.mmartinsvoboda.sporttrackingapp.presentation.components.ButtonText
import com.mmartinsvoboda.sporttrackingapp.presentation.components.CardSportAppWithTitle
import com.mmartinsvoboda.sporttrackingapp.presentation.components.ScaffoldSportApp
import com.mmartinsvoboda.sporttrackingapp.presentation.components.SpacerDefault
import com.mmartinsvoboda.sporttrackingapp.presentation.screens.destinations.ActivityListOverviewScreenDestination
import com.mmartinsvoboda.sporttrackingapp.presentation.ui.SportTrackingAppTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true) // sets this as the start destination of the default nav graph
@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator, model: LoginScreenViewModel = hiltViewModel()
) {
    val state by model.state.collectAsState()

    ScaffoldSportApp(
        topBarTitle = stringResource(id = R.string.app_name),
        topBarDisplayNavigationIcon = false,
        topBarActions = {
            val expanded = rememberSaveable { mutableStateOf(false) }

            Box(
                Modifier.wrapContentSize(Alignment.TopEnd)
            ) {
                IconButton(onClick = { expanded.value = true }) {
                    Icon(
                        Icons.Default.MoreVert, contentDescription = null
                    )
                }

                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false },
                    modifier = Modifier.clip(RoundedCornerShape(12.dp))
                ) {
                    DropdownMenuItem(onClick = {
                        expanded.value = false
                    }) {
                        Text("Settings")
                    }
                }
            }
        },
        navigator = navigator
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item { SpacerDefault() }

            item {
                val maxHeight = (LocalConfiguration.current.screenHeightDp * 0.3f).dp

                val maxWidth =
                    if (LocalConfiguration.current.screenWidthDp.dp > 600.dp) LocalConfiguration.current.screenWidthDp.dp * 0.5f else 600.dp * 0.5f

                Image(
                    painter = painterResource(id = R.drawable.activity),
                    contentDescription = "Application logo",
                    modifier = Modifier
                        .padding(bottom = SportTrackingAppTheme.paddings.largePadding)
                        .heightIn(max = maxHeight)
                        .widthIn(max = maxWidth)
                        .fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }

            item {
                CardSportAppWithTitle(
                    title = "Log in",
                    modifier = Modifier.padding(horizontal = SportTrackingAppTheme.paddings.defaultPadding)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(SportTrackingAppTheme.paddings.defaultPadding)
                    ) {
                        OutlinedTextField(value = state.user,
                            onValueChange = {
                                model.onEvent(LoginEvent.UpdateUser(it))
                            },
                            modifier = Modifier.fillMaxWidth(),
                            label = {
                                Text(text = "Username")
                            },
                            singleLine = true,
                            shape = RoundedCornerShape(12.dp),
                            enabled = !state.isLoginInProgress
                        )

                        SpacerDefault()

                        Button(
                            onClick = {
                                model.onEvent(LoginEvent.Login() {
                                    navigator.navigate(ActivityListOverviewScreenDestination)
                                })
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

            item {
                SpacerDefault()
            }
        }
    }

    if (state.isLoginInProgress) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color(0x59000000)
                )
                .clickable() { }, contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}