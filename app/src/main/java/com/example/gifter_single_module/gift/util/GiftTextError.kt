package com.example.gifter_single_module.gift.util

data class GiftTextError(
    val title: TextError = TextError(),
    val description: TextError = TextError(),
    val ownerName: TextError = TextError(),
    val mark: TextError = TextError(),
    val price: TextError = TextError(),
    val url: TextError = TextError(),
)
