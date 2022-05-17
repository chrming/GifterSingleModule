package com.example.gifter_single_module.gift.gift_detail.presentation

data class GiftTextFieldState(
    val text: String = "",
    val hint: String = "Enter ",
    val isHintVisible: Boolean = true
)