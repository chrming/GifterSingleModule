package com.example.gifter_single_module.gift.gift_list.util

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

object TextError {
    var titleError: MutableState<Boolean> = mutableStateOf(false)
    var ownerNameError: MutableState<Boolean> = mutableStateOf(false)
    var descriptionError: MutableState<Boolean> = mutableStateOf(false)
    var markError: MutableState<Boolean> = mutableStateOf(false)
    var priceError: MutableState<Boolean> = mutableStateOf(false)
}