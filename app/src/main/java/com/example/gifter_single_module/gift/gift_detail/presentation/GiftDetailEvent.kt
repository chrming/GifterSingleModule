package com.example.gifter_single_module.gift.gift_detail.presentation

import androidx.compose.ui.focus.FocusState

sealed class GiftDetailEvent {
    // title
    data class EnteredTitle(val value: String) : GiftDetailEvent()
    data class ChangedTitleFocus(val focusState: FocusState): GiftDetailEvent()

    // description
    data class EnteredDescription(val value: String): GiftDetailEvent()
    data class ChangedDescriptionFocus(val focusState: FocusState): GiftDetailEvent()

    // owner name
    data class EnteredOwnerName(val value: String) : GiftDetailEvent()
    data class ChangedOwnerName(val focusState: FocusState): GiftDetailEvent()

    // price
    data class ChangedPriceFocus(val focusState: FocusState): GiftDetailEvent()
    data class EnteredPrice(val value: String): GiftDetailEvent()

    // mark
    data class EnteredMark(val value: String): GiftDetailEvent()
    data class ChangedMarkFocus(val focusState: FocusState): GiftDetailEvent()

    // save gift
    object SaveGift: GiftDetailEvent()
}
