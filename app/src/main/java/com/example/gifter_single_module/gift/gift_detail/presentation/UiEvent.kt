package com.example.gifter_single_module.gift.gift_detail.presentation

sealed class UiEvent {
    data class ShowSnackbar(val message: String): UiEvent()
    object SaveGift: UiEvent()
}