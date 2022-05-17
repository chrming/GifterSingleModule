package com.example.gifter_single_module.gift.gift_list.presentation

import com.example.gifter_single_module.gift.gift_detail.model.Gift
import com.example.gifter_single_module.gift.gift_list.util.GiftsOrder
import com.example.gifter_single_module.util.OrderType

data class GiftListState(
    val gifts: List<Gift> = emptyList(),
    val giftsOrder: GiftsOrder = GiftsOrder.Owner(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)