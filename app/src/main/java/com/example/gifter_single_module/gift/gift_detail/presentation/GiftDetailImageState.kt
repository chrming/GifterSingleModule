package com.example.gifter_single_module.gift.gift_detail.presentation

data class GiftDetailImageState(
    val uploadOption: ImageSource = ImageSource.NONE,
    val source: String = ""
)

enum class ImageSource {
    URL, STORAGE, NONE
}