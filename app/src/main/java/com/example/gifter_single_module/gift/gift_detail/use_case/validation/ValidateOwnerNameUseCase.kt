package com.example.gifter_single_module.gift.gift_detail.use_case.validation

class ValidateOwnerNameUseCase {
    operator fun invoke(ownerName: String): Result {
        if (ownerName.isBlank() || ownerName.isEmpty()) {
            return Result(successful = false, errorMessages = "Owner name cannot be blank or empty.")
        }
        return Result(successful = true)
    }
    data class Result(
        val successful: Boolean,
        val errorMessages: String? = null
    )
}
