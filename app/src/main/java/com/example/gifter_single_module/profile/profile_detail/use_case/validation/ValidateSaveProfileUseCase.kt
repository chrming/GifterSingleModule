package com.example.gifter_single_module.profile.profile_detail.use_case.validation

import com.example.gifter_single_module.profile.profile_detail.model.InvalidProfileException
import com.example.gifter_single_module.profile.util.TextError

class ValidateSaveProfileUseCase {

    @Throws(InvalidProfileException::class)
    operator fun invoke(textError: TextError): Result {
        if (listOf(
                textError.nameError,
                textError.birthdayDateError,
                textError.namedayDateError,
            ).any { it }
        ) {
            throw InvalidProfileException(message = "Invalid data")
        }
        return Result(isSuccess = true)
    }

    data class Result(
        val isSuccess: Boolean,
        val errorMessages: String? = null
    )
}