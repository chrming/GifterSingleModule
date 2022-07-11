package com.example.gifter_single_module.profile.profile_add_edit.use_case.validation

import com.example.gifter_single_module.profile.util.MaxChars
import com.example.gifter_single_module.util.TextError

class ValidateNamedayDateUseCase {
    operator fun invoke(date: String): TextError {
        if (date.isBlank()) {
            return TextError(isError = true, errorMessage = "Date cannot be blank.")
        }
        if (date.length > MaxChars.namedayDate) {
            return TextError(
                isError = true,
                errorMessage = "Date can contain ${MaxChars.namedayDate} characters."
            )
        }
        if (date.contains(regex = Regex("""([^\d])+"""))) {
            return TextError(
                isError = true,
                errorMessage = "Date can contain only digits"
            )
        }
        return TextError(isError = false)
    }
}
//TODO validate day and month