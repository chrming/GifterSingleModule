package com.example.gifter_single_module.gift.util

import com.example.gifter_single_module.util.TextError

data class GiftTextError(
    val title: TextError = TextError(),
    val description: TextError = TextError(),
    val ownerName: TextError = TextError(),
    val mark: TextError = TextError(),
    val price: TextError = TextError(),
    val url: TextError = TextError(),
)
