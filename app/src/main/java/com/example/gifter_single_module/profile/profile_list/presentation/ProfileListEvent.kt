package com.example.gifter_single_module.profile.profile_list.presentation

import com.example.gifter_single_module.profile.model.Profile
import com.example.gifter_single_module.profile.profile_list.util.ProfileOrder

sealed class ProfileListEvent {
    data class Order(val profileOrder: ProfileOrder) : ProfileListEvent()
    data class DeleteProfile(val profile: Profile) : ProfileListEvent()
    object RestoreProfile : ProfileListEvent()
    object ToggleOrderSection : ProfileListEvent()
}