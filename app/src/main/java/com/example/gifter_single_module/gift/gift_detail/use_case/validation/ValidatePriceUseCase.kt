package com.example.gifter_single_module.gift.gift_detail.use_case.validation

class ValidatePriceUseCase {
    operator fun invoke(price: String): Result {
        if (price.isBlank() || price.isEmpty()) {
            return Result(isSuccess = false, errorMessages = "Price cannot be blank or empty.")
        }
        if (!price.contains(regex = Regex("""[\d]+(\.[\d]{0,2})$"""))) {
            return Result(
                isSuccess = false,
                errorMessages = "Price must be a number with two decimal places."
            )
        }
        return Result(isSuccess = true)
    }

    data class Result(
        val isSuccess: Boolean,
        val errorMessages: String? = null
    )
}