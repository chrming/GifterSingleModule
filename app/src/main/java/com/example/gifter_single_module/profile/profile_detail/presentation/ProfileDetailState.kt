package com.example.gifter_single_module.profile.profile_detail.presentation

import com.example.gifter_single_module.profile.profile_detail.model.Profile
import com.example.gifter_single_module.profile.profile_list.util.ProfileOrder
import com.example.gifter_single_module.util.OrderType

data class ProfileDetailState(
    val profile: List<Profile> = emptyList(),
    val profileOrder: ProfileOrder = ProfileOrder.Name(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)