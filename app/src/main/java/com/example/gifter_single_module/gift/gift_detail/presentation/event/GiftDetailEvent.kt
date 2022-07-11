package com.example.gifter_single_module.gift.gift_detail.presentation.event

sealed class GiftDetailEvent {
    // title
    data class EnteredTitle(val value: String) : GiftDetailEvent()

    // description
    data class EnteredDescription(val value: String) : GiftDetailEvent()

    // owner name
    data class EnteredOwnerName(val value: String) : GiftDetailEvent()

    // price
    data class EnteredPrice(val value: String) : GiftDetailEvent()

    // mark
    data class EnteredMark(val value: String) : GiftDetailEvent()

    // picture alert
    data class IsAlert(val value: Boolean) : GiftDetailEvent()

    // URL
    data class EnteredUrl(val value: String) : GiftDetailEvent()

    // Storage
    data class EnteredStoragePath(val value: String) : GiftDetailEvent()

    // URL / Storage request cancel
    object CanceledRequest : GiftDetailEvent()

    // URL / Storage request submit
    object SubmitRequest : GiftDetailEvent()

    // save gift
    object SaveGift : GiftDetailEvent()
}
