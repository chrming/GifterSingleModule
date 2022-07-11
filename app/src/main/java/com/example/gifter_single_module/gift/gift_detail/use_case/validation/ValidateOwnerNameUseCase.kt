package com.example.gifter_single_module.gift.gift_detail.use_case.validation

import com.example.gifter_single_module.gift.util.TextError

class ValidateOwnerNameUseCase {
    operator fun invoke(ownerName: String): TextError {
        if (ownerName.isBlank() || ownerName.isEmpty()) {
            return TextError(isError = true, errorMessage = "Owner name cannot be blank or empty.")
        }
        return TextError(isError = false)
    }
}
