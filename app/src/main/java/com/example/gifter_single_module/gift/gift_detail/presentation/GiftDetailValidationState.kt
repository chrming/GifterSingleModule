package com.example.gifter_single_module.gift.gift_detail.presentation

data class GiftDetailValidationState(
    val title: String = "",
    val titleError: String? = null,
    val ownerName: String = "None",
    val ownerNameError: String? = null,
    val description: String = "",
    val descriptionError: String? = null,
    val mark: String = "",
    val markError: String? = null,
    val price: String = "",
    val priceError: String? = null
)