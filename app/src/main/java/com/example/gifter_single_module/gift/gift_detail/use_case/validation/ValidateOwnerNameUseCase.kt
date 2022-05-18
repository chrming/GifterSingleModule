package com.example.gifter_single_module.gift.gift_detail.use_case.validation

class ValidateOwnerNameUseCase {
    operator fun invoke(ownerName: String): Result {
        if (ownerName.isBlank() || ownerName.isEmpty()) {
            return Result(isSuccess = false, errorMessages = "Owner name cannot be blank or empty.")
        }
        return Result(isSuccess = true)
    }
    data class Result(
        val isSuccess: Boolean,
        val errorMessages: String? = null
    )
}
