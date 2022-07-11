package com.example.gifter_single_module.profile.profile_add_edit.use_case.validation

import com.example.gifter_single_module.profile.model.InvalidProfileException
import com.example.gifter_single_module.profile.util.ProfileTextError
import com.example.gifter_single_module.util.TextError

class ValidateSaveProfileUseCase {

    @Throws(InvalidProfileException::class)
    operator fun invoke(profileTextError: ProfileTextError): TextError {
        if (listOf(
                profileTextError.name.isError,
                profileTextError.birthdayDate.isError,
                profileTextError.namedayDate.isError
            ).any { it }
        ) {
            throw InvalidProfileException(message = "Invalid data")
        }
        return TextError(isError = false)
    }
}