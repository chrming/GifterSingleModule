package com.example.gifter_single_module.gift.gift_list.presentation.event

import com.example.gifter_single_module.gift.model.Gift
import com.example.gifter_single_module.gift.gift_list.util.GiftsOrder

sealed class GiftListEvent {
    data class Order(val giftsOrder: GiftsOrder) : GiftListEvent()
    data class DeleteGift(val gift: Gift) : GiftListEvent()
    object RestoreGift : GiftListEvent()
    object ToggleOrderSection : GiftListEvent()
}