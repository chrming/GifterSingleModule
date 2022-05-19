package com.example.gifter_single_module.gift.gift_list.presentation

sealed class UiEvent {
    data class ShowSnackbar(val message: String): UiEvent()
}