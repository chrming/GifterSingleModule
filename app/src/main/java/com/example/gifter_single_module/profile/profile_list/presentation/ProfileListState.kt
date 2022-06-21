package com.example.gifter_single_module.profile.profile_list.presentation

import com.example.gifter_single_module.profile.model.Profile
import com.example.gifter_single_module.profile.profile_list.util.ProfileOrder
import com.example.gifter_single_module.util.OrderType

data class ProfileListState(
    val profileList: List<Profile> = emptyList(),
    val profileOrder: ProfileOrder = ProfileOrder.Name(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)