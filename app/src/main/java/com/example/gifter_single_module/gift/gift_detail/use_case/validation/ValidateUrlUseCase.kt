package com.example.gifter_single_module.gift.gift_detail.use_case.validation

import com.example.gifter_single_module.gift.util.MaxChars

class ValidateUrlUseCase {
    operator fun invoke(url: String): Result {
/*        if (url.isBlank() || url.isEmpty()) {
            return Result(
                isSuccess = false,
                errorMessages = "URL cannot be blank or empty."
            )
        }*/
        if (!url.contains(regex = Regex("""((https://www.|https://)?(.)|())"""))) {
            return Result(
                isSuccess = false,
                errorMessages = "Invalid URL"
            )
        }
        return Result(isSuccess = true)
    }

    data class Result(
        val isSuccess: Boolean,
        val errorMessages: String? = null
    )
}