package com.example.gifter_single_module.gift.util

data class TextError(
    val titleError: Boolean = false,
    val titleErrorMessage: String? = null,
    val ownerNameError: Boolean = false,
    val ownerNameErrorMessage: String? = null,
    val descriptionError: Boolean = false,
    val descriptionErrorMessage: String? = null,
    val markError: Boolean = false,
    val markErrorMessage: String? = null,
    val priceError: Boolean = false,
    val priceErrorMessage: String? = null,
    val urlError: Boolean = false,
    val urlErrorMessage: String? = null
)
