package com.example.gifter_single_module.gift.gift_detail.use_case.validation

import com.example.gifter_single_module.gift.model.InvalidGiftException
import com.example.gifter_single_module.gift.util.TextError


class ValidateSaveGiftUseCase {
    @Throws(InvalidGiftException::class)
    operator fun invoke(textError: TextError): Result {
        if (listOf(
                textError.titleError,
                textError.descriptionError,
                textError.ownerNameError,
                textError.markError,
                textError.priceError
            ).any { it }
        ) {
            throw InvalidGiftException(message = "Invalid data")
        }
        return Result(isSuccess = true)
    }

    data class Result(
        val isSuccess: Boolean,
        val errorMessages: String? = null
    )
}