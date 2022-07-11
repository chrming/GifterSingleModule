package com.example.gifter_single_module.gift.gift_detail.presentation.state

data class GiftDetailImageAlertState(
    var isAlert: Boolean = false,
    var storage: Boolean = false,
    var url: Boolean = false,
)