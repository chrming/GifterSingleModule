package com.example.gifter_single_module.gift.gift_list.view_model

import com.example.gifter_single_module.gift.gift_detail.model.Gift
import com.example.gifter_single_module.gift.gift_list.util.GiftsOrder

sealed class GiftListEvent {
    data class Order(val giftsOrder: GiftsOrder) : GiftListEvent()
    data class DeleteGift(val gift: Gift) : GiftListEvent()
    object RestoreGift : GiftListEvent()
    object ToggleOrderSection : GiftListEvent()
}