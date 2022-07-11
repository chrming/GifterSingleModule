package com.example.gifter_single_module.gift.common.state

data class ImageState(
    val uploadOption: ImageSource = ImageSource.NONE,
    val source: String? = null
)

enum class ImageSource {
    URL, STORAGE, NONE
}