package com.example.gifter_single_module.profile.profile_detail.view_model

import com.example.gifter_single_module.profile.profile_detail.model.Profile
import com.example.gifter_single_module.profile.profile_list.util.ProfilesOrder

sealed class ProfilesEvent {
    data class Order(val profilesOrder: ProfilesOrder) : ProfilesEvent()
    data class DeleteProfile(val profile: Profile) : ProfilesEvent()
    object RestoreProfile : ProfilesEvent()
    object ToggleOrderSection : ProfilesEvent()
}