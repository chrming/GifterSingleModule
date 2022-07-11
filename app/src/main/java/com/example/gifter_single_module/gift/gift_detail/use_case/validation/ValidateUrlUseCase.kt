package com.example.gifter_single_module.gift.gift_detail.use_case.validation

import com.example.gifter_single_module.gift.util.TextError

class ValidateUrlUseCase {
    operator fun invoke(url: String): TextError {
        if (!url.contains(regex = Regex("""((https://www.|https://)?(.))"""))) {
            return TextError(
                isError = true,
                errorMessage = "Invalid URL"
            )
        }
        return TextError(isError = false)
    }
}