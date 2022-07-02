package com.example.gifter_single_module.gift.gift_detail.presentation

data class GiftDetailImageAlertState(
    var isAlert: Boolean = false,
    var storage: Boolean = false,
    var url: Boolean = false,
    val result: Boolean = storage && url
) {
    fun setFalse() {
        isAlert = false
        storage = false
        url = false
    }
}
