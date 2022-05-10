package com.example.gifter_single_module.gift.gift_list.use_case

data class GiftUseCaseWrapper(
    val getGifts: GetGiftsUseCase,
    val getGift: GetGiftUseCase,
    val deleteGift: DeleteGiftUseCase,
    val addEditGift: AddEditGiftUseCase
)