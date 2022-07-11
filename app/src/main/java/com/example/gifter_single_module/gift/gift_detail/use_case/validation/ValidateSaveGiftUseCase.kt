package com.example.gifter_single_module.gift.gift_detail.use_case.validation

import com.example.gifter_single_module.gift.model.InvalidGiftException
import com.example.gifter_single_module.gift.util.GiftTextError
import com.example.gifter_single_module.gift.util.TextError


class ValidateSaveGiftUseCase {
    @Throws(InvalidGiftException::class)
    operator fun invoke(giftTextError: GiftTextError): TextError {
        if (listOf(
                giftTextError.title.isError,
                giftTextError.description.isError,
                giftTextError.ownerName.isError,
                giftTextError.mark.isError,
                giftTextError.price.isError
            ).any { it }
        ) {
            throw InvalidGiftException(message = "Invalid data")
        }
        return TextError(isError = false)
    }
}