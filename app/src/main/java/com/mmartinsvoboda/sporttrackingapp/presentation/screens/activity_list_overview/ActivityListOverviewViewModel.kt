package com.mmartinsvoboda.sporttrackingapp.presentation.screens.activity_list_overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmartinsvoboda.sporttrackingapp.common.data.Resource
import com.mmartinsvoboda.sporttrackingapp.domain.manager.UserManager
import com.mmartinsvoboda.sporttrackingapp.domain.use_case.activity_remove.ActivityRemoveUseCase
import com.mmartinsvoboda.sporttrackingapp.domain.use_case.activity_sync_off.ActivitySyncOffUseCase
import com.mmartinsvoboda.sporttrackingapp.domain.use_case.activity_sync_on.ActivitySyncOnUseCase
import com.mmartinsvoboda.sporttrackingapp.domain.use_case.get_activities_list.GetActivitiesListUseCase
import com.mmartinsvoboda.sporttrackingapp.domain.use_case.user.user_log_out.UserLogOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityListOverviewViewModel @Inject constructor(
    private val getActivitiesListUseCase: GetActivitiesListUseCase,
    private val activityRemoveUseCase: ActivityRemoveUseCase,
    private val activitySyncOnUseCase: ActivitySyncOnUseCase,
    private val activitySyncOffUseCase: ActivitySyncOffUseCase,
    private val userLogOutUseCase: UserLogOutUseCase,
    private val userManager: UserManager
) : ViewModel() {

    private var _state = MutableStateFlow(ActivityListState())
    val state: StateFlow<ActivityListState> = _state

    init {
        viewModelScope.launch {
            _state.value = state.value.copy(user = userManager.getUserName())
        }

        onEvent(ActivityListEvent.LoadActivityList(false))
    }

    fun onEvent(event: ActivityListEvent) {
        when (event) {
            is ActivityListEvent.LoadActivityList -> {
                viewModelScope.launch {
                    getActivitiesList(event.refresh)
                }
            }
            is ActivityListEvent.ActivityDelete -> {
                viewModelScope.launch {
                    _state.value = state.value.copy(isActionInProgress = true)
                    activityRemoveUseCase(event.sportActivity)
                    _state.value = state.value.copy(isActionInProgress = false)
                }
            }
            is ActivityListEvent.ActivitySyncOff -> {
                viewModelScope.launch {
                    _state.value = state.value.copy(isActionInProgress = true)
                    activitySyncOffUseCase(event.sportActivity)
                    _state.value = state.value.copy(isActionInProgress = false)
                }
            }
            is ActivityListEvent.ActivitySyncOn -> {
                viewModelScope.launch {
                    _state.value = state.value.copy(isActionInProgress = true)
                    activitySyncOnUseCase(event.sportActivity)
                    _state.value = state.value.copy(isActionInProgress = false)

                }
            }
            is ActivityListEvent.UserLogOut -> {
                viewModelScope.launch {
                    if (userLogOutUseCase()) event.onSuccessfulLogOut()
                }
            }
        }
    }

    private suspend fun getActivitiesList(fetchFromRemote: Boolean) {
        getActivitiesListUseCase(fetchFromRemote)?.onEach { result ->
            when (result.status) {
                Resource.Status.SUCCESS -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        activities = result.data ?: emptyList()
                    )
                }
                Resource.Status.ERROR -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        error = result.throwable?.localizedMessage ?: "Unknown error"
                    )
                }
                Resource.Status.LOADING -> {
                    _state.value = state.value.copy(isLoading = true)
                }
            }
        }?.launchIn(viewModelScope)
    }
}