package com.example.gifter_single_module.gift.gift_detail.use_case.validation

import com.example.gifter_single_module.gift.util.MaxChars
import com.example.gifter_single_module.util.TextError

class ValidateMarkUseCase {
    operator fun invoke(mark: String): TextError {
        if (mark.length > MaxChars.mark)
        {
            return TextError(
                isError = true,
                errorMessage = "Mark can contain ${MaxChars.mark} characters."
            )
        }
        if (mark.contains(regex = Regex("""([^\w\d\s\\!?.,\-+=_"':])+"""))) {
            return TextError(
                isError = true,
                errorMessage = "Allowed special characters: !?.,-+=_'\""
            )
        }
        return TextError(isError = false)
    }
}