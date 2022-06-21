package com.example.gifter_single_module.profile.profile_list.presentation

sealed class UiEvent {
    data class ShowSnackbar(val message: String): UiEvent()
}