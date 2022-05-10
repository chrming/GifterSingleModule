package com.example.gifter_single_module.profile.profile_list.util

import com.example.gifter_single_module.util.OrderType

sealed class ProfilesOrder(val orderType: OrderType) {
    class Title(orderType: OrderType) : ProfilesOrder(orderType)
    class Name(orderType: OrderType) : ProfilesOrder(orderType)
}