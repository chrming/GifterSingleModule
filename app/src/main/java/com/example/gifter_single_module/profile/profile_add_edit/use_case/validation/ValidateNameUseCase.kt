package com.example.gifter_single_module.profile.profile_add_edit.use_case.validation

import com.example.gifter_single_module.profile.util.MaxChars
import com.example.gifter_single_module.util.TextError

class ValidateNameUseCase {
    operator fun invoke(name: String): TextError {
        if (name.isBlank()) {
            return TextError(isError = true, errorMessage = "Name cannot be blank.")
        }
        if (name.length > MaxChars.name) {
            return TextError(
                isError = true,
                errorMessage = "Name can contain ${MaxChars.name} characters."
            )
        }
        if (name.contains(regex = Regex("""([^\w\d\s\\!?.,\-+=_"':])+"""))) {
            return TextError(
                isError = true,
                errorMessage = "Allowed special characters: !?.,-+=_'\""
            )
        }
        return TextError(isError = false)
    }
}
// TODO Refactor delete one of the date validation making one date validation using string resources