package com.example.gifter_single_module.profile.profile_detail.presentation.state

import com.example.gifter_single_module.gift.model.Gift
import com.example.gifter_single_module.profile.profile_list.util.ProfileOrder
import com.example.gifter_single_module.util.OrderType

data class ProfileGiftListState(
    var profileGiftList: List<Gift> = emptyList(),
    val profileOrder: ProfileOrder = ProfileOrder.Name(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)