package com.example.gifter_single_module.gift.gift_list.util

import com.example.gifter_single_module.util.OrderType

sealed class GiftsOrder(val orderType: OrderType) {
    class Title(orderType: OrderType) : GiftsOrder(orderType)
    class Owner(orderType: OrderType) : GiftsOrder(orderType)
    class Price(orderType: OrderType) : GiftsOrder(orderType)

    fun copy(orderType: OrderType): GiftsOrder {
        return when (this) {
            is Title -> Title(orderType)
            is Owner -> Owner(orderType)
            is Price -> Price(orderType)
        }
    }
}