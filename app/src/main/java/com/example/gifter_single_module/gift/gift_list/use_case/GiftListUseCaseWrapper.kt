package com.example.gifter_single_module.gift.gift_list.use_case

import com.example.gifter_single_module.gift.gift_detail.use_case.AddEditGiftUseCase

data class GiftListUseCaseWrapper(
    val getGifts: GetGiftsUseCase,
    val deleteGift: DeleteGiftUseCase,
    val addEditGift: AddEditGiftUseCase
)