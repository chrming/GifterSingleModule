package com.example.gifter_single_module.profile.profile_list.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gifter_single_module.profile.model.Profile
import com.example.gifter_single_module.profile.profile_list.use_case.ProfileListUseCaseWrapper
import com.example.gifter_single_module.profile.profile_list.util.ProfileOrder
import com.example.gifter_single_module.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileListViewModel @Inject constructor(
    private val profileListUseCase: ProfileListUseCaseWrapper
) : ViewModel() {

    private val _profileListState = mutableStateOf(ProfileListState())
    val profileListState = _profileListState

    private var lastDeletedProfile: Profile? = null

    private var getProfileJob: Job? = null

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow

    init {
        getProfiles(ProfileOrder.Name(OrderType.Descending))
    }

    fun onEvent(event: ProfileListEvent) {
        when (event) {
            is ProfileListEvent.Order -> {
                if (
                    _profileListState.value.profileOrder::class == event.profileOrder::class &&
                    _profileListState.value.profileOrder.orderType == event.profileOrder.orderType
                ) {
                    return
                }
                getProfiles(event.profileOrder)
            }
            is ProfileListEvent.ToggleOrderSection -> {
                viewModelScope.launch {
                    _profileListState.value = profileListState.value.copy(
                        isOrderSectionVisible = !profileListState.value.isOrderSectionVisible
                    )
                }
            }
            is ProfileListEvent.DeleteProfile -> {
                viewModelScope.launch {
                    lastDeletedProfile = event.profile
                    profileListUseCase.deleteProfile(event.profile)
                    _eventFlow.emit(UiEvent.ShowSnackbar(message = "Profile has been deleted"))
                }
            }
            is ProfileListEvent.RestoreProfile -> {
                viewModelScope.launch {
                    profileListUseCase.addEditProfile(lastDeletedProfile ?: return@launch)
                    lastDeletedProfile = null
                }
            }
        }
    }

    private fun getProfiles(profileOrder: ProfileOrder) {
        getProfileJob?.cancel()
        getProfileJob = profileListUseCase.getProfiles(profileOrder = profileOrder)
            .onEach { profiles ->
                _profileListState.value = profileListState.value.copy(
                    profileList = profiles,
                    profileOrder = profileOrder
                )
            }
            .launchIn(viewModelScope)
    }
}