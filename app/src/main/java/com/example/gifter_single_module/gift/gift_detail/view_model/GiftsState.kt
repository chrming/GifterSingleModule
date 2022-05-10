package com.example.gifter_single_module.gift.gift_detail.view_model

import com.example.gifter_single_module.gift.gift_detail.model.Gift
import com.example.gifter_single_module.gift.gift_list.util.GiftsOrder
import com.example.gifter_single_module.util.OrderType

data class GiftsState(
    val gifts: List<Gift> = emptyList(),
    val giftsOrder: GiftsOrder = GiftsOrder.Owner(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)