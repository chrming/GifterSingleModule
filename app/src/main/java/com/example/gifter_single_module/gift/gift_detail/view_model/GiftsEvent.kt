package com.example.gifter_single_module.gift.gift_detail.view_model

import com.example.gifter_single_module.gift.gift_detail.model.Gift
import com.example.gifter_single_module.gift.gift_list.util.GiftsOrder

sealed class GiftsEvent {
    data class Order(val giftsOrder: GiftsOrder) : GiftsEvent()
    data class DeleteGift(val gift: Gift) : GiftsEvent()
    object RestoreGift : GiftsEvent()
    object ToggleOrderSection : GiftsEvent()
}