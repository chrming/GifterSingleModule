package com.example.gifter_single_module.profile.profile_list.util

import com.example.gifter_single_module.util.OrderType

sealed class ProfileOrder(val orderType: OrderType) {
    class Title(orderType: OrderType) : ProfileOrder(orderType)
    class Name(orderType: OrderType) : ProfileOrder(orderType)
}