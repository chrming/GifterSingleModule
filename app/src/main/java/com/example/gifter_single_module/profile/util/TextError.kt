package com.example.gifter_single_module.profile.util

data class TextError(
    val nameError: Boolean = false,
    val nameErrorMessage: String? = null,
    val birthdayDateError: Boolean = false,
    val birthdayDateErrorMessage: String? = null,
    val namedayDateError: Boolean = false,
    val namedayDateErrorMessage: String? = null,
)
