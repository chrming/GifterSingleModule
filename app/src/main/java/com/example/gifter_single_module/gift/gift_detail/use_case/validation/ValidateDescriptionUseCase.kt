package com.example.gifter_single_module.gift.gift_detail.use_case.validation

import com.example.gifter_single_module.gift.util.MaxChars
import com.example.gifter_single_module.gift.util.TextError

class ValidateDescriptionUseCase {
    operator fun invoke(description: String): TextError {
        if (description.length > MaxChars.description) {
            return TextError(
                isError = true,
                errorMessage = "Description can contain ${MaxChars.description} characters."
            )
        }
        if (description.isBlank() || description.isEmpty()) {
            return TextError(
                isError = true,
                errorMessage = "Description cannot be blank or empty."
            )
        }
        if (description.contains(regex = Regex("""([^\w\d\s\\!?.,\-+=_"':])+"""))) {
            return TextError(
                isError = true,
                errorMessage = "Allowed special characters: !?.,-+=_'\""
            )
        }
        return TextError(isError = false)
    }
}