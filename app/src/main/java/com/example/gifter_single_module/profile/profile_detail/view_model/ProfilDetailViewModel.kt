package com.example.gifter_single_module.profile.profile_detail.view_model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gifter_single_module.profile.profile_detail.model.Profile
import com.example.gifter_single_module.profile.profile_list.use_case.ProfileUseCaseWrapper
import com.example.gifter_single_module.profile.profile_list.util.ProfilesOrder
import com.example.gifter_single_module.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfilesViewModel @Inject constructor(
    private val profilesUseCase: ProfileUseCaseWrapper
) : ViewModel() {

    private val _profilesState = mutableStateOf(ProfilesState())
    val profilesState = _profilesState

    private var lastDeletedProfile: Profile? = null

    private var getProfilesJob: Job? = null

    init {
        getProfiles(ProfilesOrder.Name(OrderType.Descending))
    }

    fun onEvent(event: ProfilesEvent) {
        when (event) {
            is ProfilesEvent.Order -> {
                if (
                    _profilesState.value.profilesOrder::class == event.profilesOrder::class
                    && profilesState.value.profilesOrder.orderType == event.profilesOrder.orderType
                ) {
                    return
                }
                getProfiles(event.profilesOrder)
            }
            is ProfilesEvent.ToggleOrderSection -> {
                viewModelScope.launch {
                    _profilesState.value = profilesState.value.copy(
                        isOrderSectionVisible = !profilesState.value.isOrderSectionVisible
                    )
                }
            }
            is ProfilesEvent.DeleteProfile -> {
                viewModelScope.launch {
                    profilesUseCase.deleteProfile(event.profile)
                    lastDeletedProfile = event.profile
                }
            }
            is ProfilesEvent.RestoreProfile -> {
                viewModelScope.launch {
                    profilesUseCase.addEditProfile(lastDeletedProfile ?: return@launch)
                    lastDeletedProfile = null
                }
            }
        }
    }

    private fun getProfiles(profilesOrder: ProfilesOrder) {
        getProfilesJob?.cancel()
        getProfilesJob = profilesUseCase.getProfiles(profilesOrder)
            .onEach { profiles ->
                _profilesState.value = profilesState.value.copy(
                    profiles = profiles,
                    profilesOrder = profilesOrder
                )
            }
            .launchIn(viewModelScope)
    }
}