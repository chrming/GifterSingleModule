package com.example.gifter_single_module.gift.gift_detail.use_case.validation

import com.example.gifter_single_module.util.TextError

class ValidatePriceUseCase {
    operator fun invoke(price: String): TextError {
        if (price.isBlank() || price.isEmpty()) {
            return TextError(isError = true, errorMessage = "Price cannot be blank or empty.")
        }
        if (!price.contains(regex = Regex("""[\d]+(\.[\d]{0,2})$"""))) {
            return TextError(
                isError = true,
                errorMessage = "Price must be a number with two decimal places."
            )
        }
        return TextError(isError = false)
    }
}