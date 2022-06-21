package com.example.gifter_single_module.profile.profile_list.util

import com.example.gifter_single_module.util.OrderType

sealed class ProfileOrder(val orderType: OrderType) {
    class Name(orderType: OrderType) : ProfileOrder(orderType)
    class Birthday(orderType: OrderType) : ProfileOrder(orderType)
    class Nameday(orderType: OrderType) : ProfileOrder(orderType)

    fun copy(orderType: OrderType): ProfileOrder {
        return when (this) {
            is Name -> Name(orderType)
            is Birthday -> Birthday(orderType)
            is Nameday -> Nameday(orderType)
        }
    }
}