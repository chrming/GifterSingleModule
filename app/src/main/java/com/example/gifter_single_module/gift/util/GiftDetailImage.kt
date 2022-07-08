package com.example.gifter_single_module.gift.util

data class GiftDetailImage(
    val uploadOption: ImageSource = ImageSource.NONE,
    val source: String? = null
)

enum class ImageSource {
    URL, STORAGE, NONE
}