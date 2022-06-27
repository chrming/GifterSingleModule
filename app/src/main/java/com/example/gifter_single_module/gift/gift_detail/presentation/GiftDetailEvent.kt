package com.example.gifter_single_module.gift.gift_detail.presentation

sealed class GiftDetailEvent {
    // title
    data class EnteredTitle(val value: String) : GiftDetailEvent()

    // description
    data class EnteredDescription(val value: String): GiftDetailEvent()

    // owner name
    data class EnteredOwnerName(val value: String) : GiftDetailEvent()

    // price
    data class EnteredPrice(val value: String): GiftDetailEvent()

    // mark
    data class EnteredMark(val value: String): GiftDetailEvent()

    // save gift
    object SaveGift: GiftDetailEvent()
}
