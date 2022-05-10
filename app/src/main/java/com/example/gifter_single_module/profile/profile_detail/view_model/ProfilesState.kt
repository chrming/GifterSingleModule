package com.example.gifter_single_module.profile.profile_detail.view_model

import com.example.gifter_single_module.profile.profile_detail.model.Profile
import com.example.gifter_single_module.profile.profile_list.util.ProfilesOrder
import com.example.gifter_single_module.util.OrderType

data class ProfilesState(
    val profiles: List<Profile> = emptyList(),
    val profilesOrder: ProfilesOrder = ProfilesOrder.Name(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)