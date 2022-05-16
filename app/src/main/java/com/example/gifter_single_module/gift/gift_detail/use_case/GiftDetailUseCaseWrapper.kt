package com.example.gifter_single_module.gift.gift_detail.use_case

data class GiftDetailUseCaseWrapper(
    val getGift: GetGiftUseCase,
    val addEditGift: AddEditGiftUseCase

)