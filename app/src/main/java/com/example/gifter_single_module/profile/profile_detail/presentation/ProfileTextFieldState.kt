package com.example.gifter_single_module.profile.profile_detail.presentation

data class ProfileTextFieldState(
    val text: String = "",
    val hint: String = "Enter ",
    val isHintVisible: Boolean = true
)