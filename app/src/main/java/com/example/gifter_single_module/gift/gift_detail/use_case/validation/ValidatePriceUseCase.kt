package com.example.gifter_single_module.gift.gift_detail.use_case.validation

class ValidatePriceUseCase {
    operator fun invoke(price: String): Result {
        if (price.isBlank() || price.isEmpty()) {
            return Result(successful = false, errorMessages = "Price cannot be blank or empty.")
        }
        if (price.contains(regex = Regex("""^((\d*)?(\.\d{2})$)"""))) {
            return Result(
                successful = false,
                errorMessages = "Price must be a number with two decimal places."
            )
        }
        return Result(successful = true)
    }

    data class Result(
        val successful: Boolean,
        val errorMessages: String? = null
    )
}