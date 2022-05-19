package com.example.gifter_single_module.gift.gift_detail.use_case

import com.example.gifter_single_module.gift.gift_detail.use_case.validation.*

data class GiftDetailUseCaseWrapper(
    val getGift: GetGiftUseCase,
    val addEditGift: AddEditGiftUseCase,
    val validateSaveGift: ValidateSaveGiftUseCase,
    val validateTitle: ValidateTitleUseCase,
    val validateDescription: ValidateDescriptionUseCase,
    val validateOwnerName: ValidateOwnerNameUseCase,
    val validateMark: ValidateMarkUseCase,
    val validatePrice: ValidatePriceUseCase
)